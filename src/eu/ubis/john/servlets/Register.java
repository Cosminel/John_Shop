package eu.ubis.john.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import eu.ubis.eshop.bfcl.FacadeFactory;
import eu.ubis.eshop.bfcl.UserDTO;
import eu.ubis.eshop.bfcl.UserFacade;

/**
 * Servlet implementation class Register
 */
@WebServlet("/RegisterServlet")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static UserFacade userFacade = FacadeFactory.getUserFacade();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
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
		
		UserDTO dto = new UserDTO();
		System.out.println(request.getParameter("username"));
		dto.setUser((String)request.getParameter("username"));
		dto.setPassword((String)request.getParameter("password"));
		dto.setEmail((String)request.getParameter("email"));
		userFacade.createUser(dto);
		
		 String encodedURL = response.encodeRedirectURL(request.getContextPath() + "/index.jsp");
    	 response.sendRedirect(encodedURL);
		
	}
	
}
