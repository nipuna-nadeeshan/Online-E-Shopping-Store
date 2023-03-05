package Entity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import SQLDriver.JDBCDriver;

public class Item {
	private int num;
	private float price;
	private int qty;
	private String name;
	private String type;
	private String photoUrl;
	public Item(int num) {
		this.num = num;
	}
	public Item(int num, float price, int qty ,String name, String type, String photoUrl) {
		this.num = num;
		this.price = price;
		this.qty = qty;
		this.name = name;
		this.type = type;
		this.photoUrl = photoUrl;
	}
	public String getHtmlCode() {
		String code = "<div class=\"col-lg-3 col-md-6 col-sm-12 pb-1\">\r\n"
				+ "                <div class=\"card product-item border-0 mb-4\">\r\n"
				+ "                    <div class=\"card-header product-img position-relative overflow-hidden bg-transparent border p-0\">\r\n"
				+ "                        <img class=\"img-fluid w-100\" src=\"img/product-8.jpg\" alt=\"\">\r\n"
				+ "                    </div>\r\n"
				+ "                    <div class=\"card-body border-left border-right text-center p-0 pt-4 pb-3\">\r\n"
				+ "                        <h6 class=\"text-truncate mb-3\">"+name+"</h6>\r\n"
				+ "                        <div class=\"d-flex justify-content-center\">\r\n"
				+ "                            <h6>$123.00</h6><h6 class=\"text-muted ml-2\"><del>$"+price+"</del></h6>\r\n"
				+ "                        </div>\r\n"
				+ "                    </div>\r\n"
				+ "                    <div class=\"card-footer d-flex justify-content-between bg-light border\">\r\n"
				+ "                    	<form action=\"add\" method=\"get\">\r\n"
				+ "                    		<input type=\"hidden\" value=\""+num+"\" name=\"name\"> \r\n"				
				+ "                        	<button class=\"btn btn-sm text-dark p-0\"><i class=\"fas fa-eye text-primary mr-1\"></i>View Detail</button>\r\n"
				+ "                        </form>\r\n"
				
				
				+ "                        <button href=\"\" class=\"btn btn-sm text-dark p-0 addButton\" id=\""+num+"\"><i class=\"fas fa-shopping-cart text-primary mr-1\" ></i>Add To Cart</button>\r\n"
				
				+ "                    </div>\r\n"
				+ "                </div>\r\n"
				+ "            </div>";
		return code;
	}
	public String getName() {
		return name;
	}public String getPhotoUrl() {
		return photoUrl;
	}public int getNum() {
		return num;
	}public float getPrice() {
		return price;
	}public int getQty() {
		return qty;
	}public String getType() {
		return type;
	}
	public void setName(String name) {
		this.name = name;
	}public void setNum(int num) {
		this.num = num;
	}public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}public void setPrice(float price) {
		this.price = price;
	}public void setQty(int qty) {
		this.qty = qty;
	}public void setType(String type) {
		this.type = type;
	}
	public static ArrayList<Item> getMatchingItems(String na){
		String query = "SELECT * FROM item WHERE item_name LIKE '%"+na+"%'";
		ArrayList<Item>  list = new  ArrayList<>();
		Connection conn = JDBCDriver.getConnection();
		Statement stmnt;
		try {
			stmnt = conn.createStatement();
			ResultSet rs = stmnt.executeQuery(query);
			while(rs.next()) {
				//int num, float price, int qty ,String name, String type, String photoUrl
				int num = rs.getInt("item_id");
				float price = rs.getFloat("price");
				int qty = rs.getInt("qty");
				String name = rs.getString("item_name");
				String type = rs.getString("type");
				String photoUrl = rs.getString("photo_url");
				list.add(new Item(num,price,qty,name,type,photoUrl));				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
		
	}
}

