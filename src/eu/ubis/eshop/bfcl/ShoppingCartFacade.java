package eu.ubis.eshop.bfcl;

import java.util.List;

public interface ShoppingCartFacade {
	public void addProduct(ProductDTO prod);
	public void removeProduct (ProductDTO prod);
	public List<ProductDTO> getAllProducts();
}
