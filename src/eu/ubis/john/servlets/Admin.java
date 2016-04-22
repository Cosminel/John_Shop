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
import eu.ubis.eshop.bfcl.ProductDTO;
import eu.ubis.eshop.bfcl.ProductFacade;

/**
 * Servlet implementation class Admin
 */
@WebServlet(name = "AdminServlet", urlPatterns = { "/AdminServlet" })
public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ProductFacade productFacade = FacadeFactory.getProductFacade();  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Admin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action.equals("getProducts")) {
			redirectToProductsPage(request, response);
		}
	}

	private void redirectToProductsPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		HttpSession session = request.getSession();

		List<ProductDTO> products = productFacade.getAllProducts();
		List<String> categories = productFacade.getAllCategories();
		List<String> subcategories = productFacade.getAllSubcategories();

		session.setAttribute("products", products);
		session.setAttribute("categories", categories);
		session.setAttribute("subcategories", subcategories);
		
		String encodedURL = response.encodeRedirectURL(request.getContextPath() + "/admin-panel.jsp");
   	 	response.sendRedirect(encodedURL);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
