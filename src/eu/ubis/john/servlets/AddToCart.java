package eu.ubis.john.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import eu.ubis.eshop.bfcl.ProductDTO;

/**
 * Servlet implementation class AddToCart
 */
@WebServlet("/AddToCartServlet")
public class AddToCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddToCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		if (action.equals("remove"))
		{
			removeProduct(request,response);
		}
			
	}



	private void addProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		List<ProductDTO> productList ;
		HttpSession session = request.getSession();
		ProductDTO produsx = new ProductDTO();
		produsx.setName(request.getParameter("name"));
		ProductDTO y= (ProductDTO)request.getAttribute("prod");
		produsx.setImagePath(request.getParameter("img"));
		produsx.setPrice(Float.parseFloat (request.getParameter("price")));
		produsx.setDescription(request.getParameter("description"));
	
		if (session.getAttribute("products")!=null)
			productList = (List<ProductDTO>)session.getAttribute("products");
		else
			productList = new ArrayList<ProductDTO>();
	

		if (!productList.contains(produsx))
			productList.add(produsx);

//		session.setAttribute("noofproducts", cartFacade.getAllProducts().size());
		session.setAttribute("products", productList);
		response.sendRedirect(request.getContextPath() + "/index.jsp");
	}
	
	private void removeProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		List<ProductDTO> productList = new ArrayList<ProductDTO>() ;
		ProductDTO produsx = new ProductDTO();
		produsx.setName(request.getParameter("name"));
		ProductDTO aux=null ;
		if (session.getAttribute("products")!=null)
			{
				productList = (List<ProductDTO>)session.getAttribute("products");
				System.out.println(productList.get(0).getName());
				System.out.println(produsx.getName());
				for(ProductDTO p: productList)
				{
					if (p.getName().equals(produsx.getName()))
						aux = p;
				}
				
				if (aux!=null)
				productList.remove(aux);
			}

		session.setAttribute("products", productList);	
		response.sendRedirect(request.getContextPath() + "/shopping-cart.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		if (action.equals("add")) {
			addProduct(request, response);
		}

	}

}
