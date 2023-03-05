package Entity;

import java.sql.Connection;
	
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;



import SQLDriver.JDBCDriver;

public class Cart {
	public static final int add = 0;
	public static final int minus = 1;
	private int userId;
	private ArrayList<CartItem> itemList;
	
	public Cart(int userId) {
		this.userId = userId;
		itemList = new ArrayList<>();
	}
	
	public ArrayList<CartItem> getItemList() {
		return itemList;
	}
	public int getUserId() {
		return userId;
	}
	
	/* Show the available item in the cart */
	
	//int num, float price, int qty ,String name, String type, String photoUrl
	public void loadCart() {
		String query = "SELECT * FROM cart c JOIN item i ON i.item_id = c.item_id WHERE c.cid = "+userId+";";
		System.out.println(query);
		Connection conn = JDBCDriver.getConnection();
		try {
			Statement stmnt = conn.createStatement();
			ResultSet rs = stmnt.executeQuery(query);
			while(rs.next()) {
				int num = rs.getInt("i.item_id");
				float price = rs.getFloat("i.price");
				int availableQty = rs.getInt("i.qty");
				String name = rs.getString("i.item_name");
				String type = rs.getString("i.type");
				String photoUrl = rs.getString("i.photo_url");
				int qty = rs.getInt("c.qty");
				
				CartItem cartItem = new CartItem(new Item(num,price,availableQty,name,type,photoUrl),qty);
				itemList.add(cartItem);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/* increase Item Quantity */
	
	public void addItemToCart(int itemId,int currentQty) {
		for(int i = 0;i<itemList.size();i++) {
			CartItem cartItem = itemList.get(i);
			Item  item = cartItem.getItem();
			if(item.getNum() == itemId) {				
				cartItem.setQty(cartItem.getQty()+currentQty);
				String query = "UPDATE cart SET qty = "+ cartItem.getQty() + " WHERE item_id = "+itemId + " AND cid = "+userId;
				System.out.println(query);
				Connection conn = JDBCDriver.getConnection();
				try {
					Statement stmnt = conn.createStatement();
					if(stmnt.execute(query)) {
						System.out.println("update unsuccessful");
					}else {
						System.out.println("update successful");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}				
		}
	}
	
	/* Delete item from the cart */
	public void removeItemFromCart(int itemId) {
		for(int i = 0; i<itemList.size();i++) {
			CartItem cartItem = itemList.get(i);
			Item  item = cartItem.getItem();
			if(item.getNum() == itemId) {
				itemList.remove(i);
				String query = "DELETE FROM cart WHERE cid = "+userId+ " AND "+ " item_id = "+itemId;
				Connection conn = JDBCDriver.getConnection();
				try {
					Statement stmnt = conn.createStatement();
					if(stmnt.execute(query)) {
						System.out.println("delete unsuccessful");
					}else {
						System.out.println("delete successful");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	/* Reduce item quantity */
	public void reduceItemQty(int itemId,int qty) {
		for(int i = 0;i<itemList.size();i++) {
			CartItem cartItem = itemList.get(i);
			Item  item = cartItem.getItem();
			if(item.getNum() == itemId) {
				int currentQty = cartItem.getQty();
				if(currentQty<=qty)
					removeItemFromCart(itemId);
				else {					
					cartItem.setQty(cartItem.getQty()-qty);
					String query = "UPDATE cart SET qty = "+ cartItem.getQty() + " WHERE item_id = "+itemId + " AND cid = "+userId;
					Connection conn = JDBCDriver.getConnection();
					try {
						Statement stmnt = conn.createStatement();
						if(stmnt.execute(query)) {
							System.out.println("update unsuccessful");
						}else {
							System.out.println("update successful");
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}				
		}
	}
	
	
	public void addNewItemToCart(int itemId,int qty) {
		boolean isAlreadyIn = false;
		for(int i = 0;i<itemList.size();i++) {
			CartItem cartItem = itemList.get(i);
			Item  item = cartItem.getItem();
			if(item.getNum() == itemId) {
				isAlreadyIn = true;
				break;
			}				
		}
		if(isAlreadyIn) {
			addItemToCart(itemId, qty);
		}else {
			String query = "INSERT INTO cart VALUES("+userId+","+itemId+","+qty+")";
			Connection conn = JDBCDriver.getConnection();
			try {
				Statement stmnt = conn.createStatement();
				if(stmnt.execute(query)) {
					System.out.println("insert unsuccessful");
				}else {
					System.out.println("insert successful");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	public static int getNumOfItems(int num) {
		Cart cart = new Cart(num);
		cart.loadCart();
		return cart.itemList.size();
	}

	
}
