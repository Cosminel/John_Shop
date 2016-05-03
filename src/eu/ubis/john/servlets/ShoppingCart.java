package eu.ubis.john.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
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
import eu.ubis.eshop.bfcl.ProductFacade;
import eu.ubis.eshop.bfcl.UserDTO;

/**
 * Servlet implementation class AddToCart
 */
@WebServlet("/ShoppingCart")
public class ShoppingCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ProductFacade productFacade = FacadeFactory.getProductFacade();
	private static OrdersFacade orderFacade = FacadeFactory.getOrderFacade();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShoppingCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		
		if (action.equals("add")) {
			addProduct(request, response);
		}
		if (action.equals("remove"))
		{
			removeProduct(request,response);
		}	
		if	(action.equals("continue"))
		{
			continueAction(request,response);
		}
	
	}
	private void continueAction(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		String url;
		Hashtable <ProductDTO,Integer> productList = (Hashtable<ProductDTO,Integer>)session.getAttribute("shoppingcart");
		if(session.getAttribute("user")==null ||productList==null)
			url=request.getContextPath()+"/shopping-cart.jsp";
		else
			{	
				UserDTO user = (UserDTO)session.getAttribute("user");
				OrderDTO order = new OrderDTO();
				order.setUserId(user.getId());
				List<ProductDTO> products = new ArrayList<ProductDTO>();
				Enumeration<ProductDTO> enumeration = productList.keys();
				while(enumeration.hasMoreElements())
				{
					ProductDTO aux = enumeration.nextElement();
					aux.setQuantity(productList.get(aux));
					products.add(aux);
				}
				order.setProducts(products);
				orderFacade.addOrder(order);
				session.setAttribute("comanda", 1);
				url=request.getContextPath()+"/purchaseOrder.jsp";
			}
		response.sendRedirect(url);
		
	}

	private void removeProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		Hashtable <ProductDTO,Integer> productList = new Hashtable <ProductDTO,Integer>();
		if (session.getAttribute("shoppingcart")!=null)
			productList = (Hashtable<ProductDTO,Integer>)session.getAttribute("shoppingcart");
		int id = Integer.parseInt(request.getParameter("id"));
		Enumeration<ProductDTO> enumeration = productList.keys();
		while(enumeration.hasMoreElements())
		{
			ProductDTO aux = enumeration.nextElement();
			if(aux.getProductId()==id)
				productList.remove(aux);
		}

		session.setAttribute("shoppingcart", productList);	
		response.sendRedirect(request.getContextPath()+"/shopping-cart.jsp");
	}
	
	private void addProduct(HttpServletRequest request, HttpServletResponse response) throws IOException 
	{
		HttpSession session = request.getSession();
		Hashtable <ProductDTO,Integer> productList;
		
		if (session.getAttribute("shoppingcart")!=null)
			productList = (Hashtable<ProductDTO,Integer>)session.getAttribute("shoppingcart");
		else
			productList = new Hashtable<ProductDTO,Integer>();
		
		ProductDTO prod = new ProductDTO();
		prod = productFacade.getProductbyId(Integer.parseInt(request.getParameter("id")));
		if (productList.containsKey(prod))
			productList.put(prod, productList.get(prod)+1);
		else
			productList.put(prod, 1);
		
			
		session.setAttribute("shoppingcart", productList);
		response.sendRedirect(request.getContextPath() + "/index.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String action = request.getParameter("action");
		if(action.equals("refresh"))
			refreshProduct(request,response);
	
	}
	private void refreshProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		int id = Integer.parseInt(request.getParameter("id"));
		HttpSession session = request.getSession();
		Hashtable <ProductDTO,Integer> productList;
		
		if (session.getAttribute("shoppingcart")!=null)
			productList = (Hashtable<ProductDTO,Integer>)session.getAttribute("shoppingcart");
		else
			productList = new Hashtable<ProductDTO,Integer>();
		
		Enumeration<ProductDTO> enumeration = productList.keys();
		while(enumeration.hasMoreElements())
		{
			ProductDTO aux = enumeration.nextElement();
			if(aux.getProductId()==id)
				productList.put(aux, quantity);
		}
		session.setAttribute("shoppingcart", productList);	
		response.sendRedirect(request.getContextPath() + "/shopping-cart.jsp");
	}

}
