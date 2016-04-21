package eu.ubis.eshop.pf;

import java.sql.Connection;

import eu.ubis.eshop.bf.domain.model.Product;
import eu.ubis.eshop.bf.domain.repo.ShoppingCartRepositoryBean;
import eu.ubis.eshop.bf.integration.repo.ConnectionHelperClass;
import eu.ubis.eshop.bfcl.FacadeFactory;
import eu.ubis.eshop.bfcl.OrdersFacade;
import eu.ubis.eshop.bfcl.ProductDTO;
import eu.ubis.eshop.bfcl.UserDTO;
import eu.ubis.eshop.bfcl.UserFacade;

public class Test1 {

	public static void main(String[] args) {
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
	
	ShoppingCartRepositoryBean ex = new ShoppingCartRepositoryBean();
	ProductDTO produs = new ProductDTO();
	produs.setName("Pepene");
	produs.setPrice(5);
	produs.setDescription("Excelent");
	ex.Add(produs);
	for (ProductDTO y :ex.getListOfProducts())
		{System.out.println(y.getName());
		
		}
	ShoppingCartRepositoryBean ex1 = new ShoppingCartRepositoryBean();
	ex1.getListOfProducts();
	
	}

}
