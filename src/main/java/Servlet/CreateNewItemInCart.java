package Servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import Entity.Cart;

/**
 * Servlet implementation class CreateNewItemInCart
 */
@WebServlet("/create-new-item-in-cart")
public class CreateNewItemInCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateNewItemInCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cart cart = new Cart(1);
		cart.loadCart();
		int itemNo = Integer.parseInt(request.getParameter("itemId"));
		cart.addNewItemToCart(itemNo, 1);
		request.setAttribute("carQty",Cart.getNumOfItems(1));
		response.getWriter().append(Integer.toString(Cart.getNumOfItems(1)));
		//RequestDispatcher rd=request.getRequestDispatcher("my-cart");
        //rd.forward(request, response);
		
	}

}
