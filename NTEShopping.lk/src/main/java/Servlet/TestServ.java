package Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import Entity.Cart;

/**
 * Servlet implementation class TestServ
 */
@WebServlet("/test")
public class TestServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestServ() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		Cart cart = new Cart(1);
//		cart.addNewItemToCart(2, 5);

//		Cart cart = new Cart(1);
//		cart.loadCart();
//		cart.addItemToCart(1, 2);
		
//		Cart cart = new Cart(1);
//		cart.loadCart();
//		cart.reduceItemQty(1, 3);
		
//		Cart cart = new Cart(1);
//		cart.loadCart();
//		cart.removeItemFromCart(1);
		
		Cart cart = new Cart(1);
		cart.loadCart();
		System.out.println(cart.getItemList().size());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
