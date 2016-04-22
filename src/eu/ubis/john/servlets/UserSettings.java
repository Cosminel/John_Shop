package eu.ubis.john.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import eu.ubis.eshop.bfcl.FacadeFactory;
import eu.ubis.eshop.bfcl.OrdersFacade;
import eu.ubis.eshop.bfcl.UserDTO;
import eu.ubis.eshop.bfcl.UserFacade;

/**
 * Servlet implementation class UserSettingsServlet
 */
@WebServlet("/UserSettingsServlet")
public class UserSettings extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static UserFacade userFacade = FacadeFactory.getUserFacade();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserSettings() {
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
		
		if (action.equals("saveProfile"))
			saveProfile(request,response);
		if(action.equals("resetPassword"))
			resetPassword(request,response);
	}

	private void resetPassword(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession sesion = request.getSession();
		String link;
		String oldPassword = request.getParameter("oldpassword");
		String newPassword = request.getParameter("newpassword");
		String confirmPassword = request.getParameter("confirmnewpassword");
		UserDTO user = (UserDTO)sesion.getAttribute("user");
		
		if (user.getPassword().equals(oldPassword)&&newPassword.equals(confirmPassword))
		{
			user.setPassword(newPassword);
			userFacade.editAccount(user);
			link = "/home.jsp";
		}
		else
			link="/userSettings.jsp";
		
		
		
		
		String encodedURL = response.encodeRedirectURL(request.getContextPath() + link);
    	response.sendRedirect(encodedURL);
	}

	private void saveProfile(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession sesion = request.getSession();
		UserDTO user = (UserDTO)sesion.getAttribute("user");
		user.setName(request.getParameter("lastname"));
		user.setFirstName(request.getParameter("firstname"));
		user.setEmail(request.getParameter("email"));
		user.setAddress(request.getParameter("address"));
		userFacade.editAccount(user);
		
		String encodedURL = response.encodeRedirectURL(request.getContextPath() + "/home.jsp");
    	response.sendRedirect(encodedURL);
	}

}
