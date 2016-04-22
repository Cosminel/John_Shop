package eu.ubis.john.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import eu.ubis.eshop.bfcl.FacadeFactory;
import eu.ubis.eshop.bfcl.OrdersFacade;
import eu.ubis.eshop.bfcl.ProductDTO;
import eu.ubis.eshop.bfcl.ProductFacade;
import eu.ubis.eshop.bfcl.ShoppingCartFacade;
import eu.ubis.eshop.bfcl.UserDTO;
import eu.ubis.eshop.bfcl.UserFacade;

/**
 * Servlet implementation class index
 */
@WebServlet("/IndexServlet")
public class Index extends HttpServlet {
	private static final String SESSIONID = "SESSIONID";

	private static final long serialVersionUID = 1L;

	private static ProductFacade productFacade = FacadeFactory.getProductFacade();

	private static OrdersFacade ordersFacade = FacadeFactory.getOrderFacade();
	
	private static UserFacade userFacade = FacadeFactory.getUserFacade();
	
	

	public List<ProductDTO> getAllProducts() {
		return productFacade.getAllProducts();
	}
	
	public List<String> getAllCategories() {
		return productFacade.getAllCategories();
	}
	
	public List<String> getSubcategoriesByCategoryName(String categoryName) {
		return productFacade.getSubcategoriesByCategoryName(categoryName);
	}
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Index() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String action = request.getParameter("action");
		if (action.equals("getProducts")) {
			redirectToProductsPage(request, response);
		}
	}
    private void redirectToProductsPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		List<ProductDTO> products = productFacade.getAllProducts();
		List<String> categories = productFacade.getAllCategories();
		List<String> subcategories = productFacade.getAllSubcategories();
		
		session.setAttribute("products", products);
		session.setAttribute("categories", categories);
		session.setAttribute("subcategories", subcategories);
		session.setAttribute("username", session.getId());

		if (request.getParameter(SESSIONID) != null) {
			Cookie userCookie = new Cookie(SESSIONID, request.getParameter(SESSIONID));
			response.addCookie(userCookie);
		} else {
			String sessionId = session.getId();
			Cookie userCookie = new Cookie(SESSIONID, sessionId);
			response.addCookie(userCookie);
		}
		
		
		response.sendRedirect(request.getContextPath() + "/index.jsp");
	}

	


}
