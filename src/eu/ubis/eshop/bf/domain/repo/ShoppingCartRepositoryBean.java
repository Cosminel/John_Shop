package eu.ubis.eshop.bf.domain.repo;

import java.util.ArrayList;
import java.util.List;

import eu.ubis.eshop.bfcl.ProductDTO;

public class ShoppingCartRepositoryBean {
	private  List <ProductDTO> listOfProducts=new ArrayList<ProductDTO>();
	
	public void Add(ProductDTO prod)
	{
		listOfProducts.add(prod);
	}
	public void remove (ProductDTO prod)
	{
		listOfProducts.remove(prod);
	}
	public List<ProductDTO> getListOfProducts() {
		return listOfProducts;
	}
	
}
