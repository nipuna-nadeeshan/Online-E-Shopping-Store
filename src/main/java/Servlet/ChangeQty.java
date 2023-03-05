package Servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import Entity.Cart;
import Entity.CartItem;

/**
 * Servlet implementation class ChangeQty
 */
@WebServlet("/add-or-remove")
public class ChangeQty extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeQty() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int itemNo = Integer.parseInt(request.getParameter("itemNo"));
		int addOrRemove = Integer.parseInt(request.getParameter("action"));
		
		Cart cart = new Cart(1);
		cart.loadCart();
		if(addOrRemove == Cart.add)
			cart.addItemToCart(itemNo, 1);
		else
			cart.reduceItemQty(itemNo,1);
		cart.loadCart();
		ArrayList<CartItem> list = cart.getItemList();
		request.setAttribute("cartList", list);
		RequestDispatcher rd=request.getRequestDispatcher("my-cart");
        rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
