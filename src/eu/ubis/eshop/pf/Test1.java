package eu.ubis.eshop.pf;

import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import eu.ubis.eshop.bf.domain.model.Product;
import eu.ubis.eshop.bf.integration.model.OrdersEntity;
import eu.ubis.eshop.bf.integration.model.ProductEntity;
import eu.ubis.eshop.bf.integration.repo.ConnectionHelperClass;
import eu.ubis.eshop.bf.integration.repo.OrdersDAOBean;
import eu.ubis.eshop.bf.integration.repo.ProductDAOBean;
import eu.ubis.eshop.bfcl.FacadeFactory;
import eu.ubis.eshop.bfcl.OrdersFacade;
import eu.ubis.eshop.bfcl.ProductDTO;
import eu.ubis.eshop.bfcl.ProductFacade;
import eu.ubis.eshop.bfcl.UserDTO;
import eu.ubis.eshop.bfcl.UserFacade;

public class Test1 {

	public static void main(String[] args) throws SQLException {
		System.out.println("salut");


//	 UserFacade userFacade = FacadeFactory.getUserFacade();
//	 UserDTO user = new UserDTO();
//	 user.setUser("iulian.gilca");
	 
//	 user.setPassword("parola");
//	 
//	 userFacade.createUser(user);
//	 userFacade.loginUser("cosmin.cazacu", "parola1");
//	 UserDTO salut  = userFacade.getCurrentUser();
//	if (salut!=null)
//		System.out.println(salut.getUser());
	
//	ShoppingCartRepositoryBean ex = new ShoppingCartRepositoryBean();
//	ProductDTO produs = new ProductDTO();
//	produs.setName("Pepene");
//	produs.setPrice(5);
//	produs.setDescription("Excelent");
//	ex.Add(produs);
//	for (ProductDTO y :ex.getListOfProducts())
//		{System.out.println(y.getName());
//		
//		}
//	ShoppingCartRepositoryBean ex1 = new ShoppingCartRepositoryBean();
//	ex1.getListOfProducts();
//		ProductFacade productFacade = FacadeFactory.getProductFacade();
//		ProductDTO prod;
//		prod = productFacade.getProductbyId(1);
//		ProductDTO prod1 = productFacade.getProductbyId(2);
//		Hashtable <ProductDTO,Integer> productList = new Hashtable<ProductDTO,Integer>();
//		productList.put(prod, 1);
//		productList.put(prod1, 1);
//		ProductDTO prod2= productFacade.getProductbyId(1);
//		if(productList.containsKey(prod2))
//			System.out.println("contine");
//		Enumeration<ProductDTO> enumKey = productList.keys();
//		while(enumKey.hasMoreElements())
//		{	ProductDTO produs = enumKey.nextElement();
//			System.out.println(produs.getName());
//		}
//		
//		ProductFacade index = FacadeFactory.getProductFacade();
//		List<String> salut =index.getSubcategoriesByCategoryName("Fructe");
//		for(String s: salut)
//			System.out.println(s);
		
//		OrdersEntity entity = new OrdersEntity();
//		entity.setUserId(1);
//		ProductEntity prod = new ProductEntity();
//		prod.setName("caciula");
//		prod.setPrice(5);
//		prod.setProductId(1);
//		prod.setQuantity(2);
//		prod.setImagePath("/link");
//		prod.setDescription("un prods bun");
//		List<ProductEntity> list = new ArrayList<ProductEntity>();
//		list.add(prod);
//		entity.setProducts(list);
//		OrdersDAOBean bean = new OrdersDAOBean();
//		try {
//			bean.addOrder(entity);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		List<OrdersEntity> lista = new ArrayList<OrdersEntity>();
		OrdersDAOBean bean = new OrdersDAOBean();
		lista = bean.getAllOrdersByUserId(1);
		for (OrdersEntity l : lista)
		{
			System.out.println(l.getUserId());
			for (ProductEntity p :l.getProducts())
			{
				System.out.println(p.getName());
			}
		}
		
	}

}
