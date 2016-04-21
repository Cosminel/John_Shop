package eu.ubis.john.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import eu.ubis.eshop.bfcl.FacadeFactory;
import eu.ubis.eshop.bfcl.UserDTO;
import eu.ubis.eshop.bfcl.UserFacade;

/**
 * Servlet implementation class Login
 */
@WebServlet("/LoginServlet")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static UserFacade userFacade = FacadeFactory.getUserFacade();
	/**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
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
		String action = request.getParameter("action");
		if (action.equals("login")) {
			redirectToLoginPage(request, response);
		}
	}
	
	private void redirectToLoginPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		 String user=request.getParameter("username");    
	     String pass=request.getParameter("password"); 
	     userFacade.loginUser(user, pass);
	     if (userFacade.getCurrentUser()!=null)
	    	 {
	    	 UserDTO currentUser = new UserDTO();
	    	 currentUser = userFacade.getCurrentUser();
	    	 session.setAttribute("user", currentUser);
	    	 String encodedURL = response.encodeRedirectURL(request.getContextPath() + "/home.jsp");
	    	 response.sendRedirect(encodedURL);
	    	 }

	     else 
	     	{
	    	 	response.sendRedirect("index.jsp");
	     	}
		
		
	}

}
