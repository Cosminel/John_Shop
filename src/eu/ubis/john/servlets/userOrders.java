package eu.ubis.john.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import eu.ubis.eshop.bfcl.FacadeFactory;
import eu.ubis.eshop.bfcl.OrderDTO;
import eu.ubis.eshop.bfcl.OrdersFacade;
import eu.ubis.eshop.bfcl.ProductDTO;
import eu.ubis.eshop.bfcl.UserDTO;

/**
 * Servlet implementation class userOrders
 */
@WebServlet("/userOrders")
public class userOrders extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static OrdersFacade orderFacade  = FacadeFactory.getOrderFacade();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public userOrders() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String id = request.getParameter("id");
		int idInt = Integer.parseInt(id) -1;

		List<OrderDTO> orders = (List<OrderDTO>)session.getAttribute("orders");
		for (int i=0;i<orders.size();i++)
		{
			if (idInt==i)
			{  
				session.setAttribute("productsOrder", orders.get(i).getProducts());
			}
		}
		
		
	}

}
