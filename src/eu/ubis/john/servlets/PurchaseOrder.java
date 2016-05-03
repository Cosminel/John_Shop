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
import eu.ubis.eshop.bfcl.UserDTO;

/**
 * Servlet implementation class PurchaseOrder
 */
@WebServlet("/PurchaseOrderServlet")
public class PurchaseOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static OrdersFacade orderFacade  = FacadeFactory.getOrderFacade();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PurchaseOrder() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("comanda");
		
		UserDTO currentUser = (UserDTO)session.getAttribute("user");
		List<OrderDTO> orders = orderFacade.getAllOrdersByUserId(currentUser.getId());
		session.setAttribute("orders", orders);
		response.sendRedirect(request.getContextPath()+"/EmailSendingServlet");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
