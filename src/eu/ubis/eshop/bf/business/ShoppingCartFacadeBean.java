package eu.ubis.eshop.bf.business;

import java.util.List;

import eu.ubis.eshop.bf.domain.repo.ShoppingCartRepositoryBean;
import eu.ubis.eshop.bfcl.ProductDTO;
import eu.ubis.eshop.bfcl.ShoppingCartFacade;

public class ShoppingCartFacadeBean implements ShoppingCartFacade {
	private static final ShoppingCartRepositoryBean cartRepository = new ShoppingCartRepositoryBean();
	@Override
	public void addProduct(ProductDTO prod) {
		cartRepository.Add(prod);
		
	}

	@Override
	public void removeProduct(ProductDTO prod) {
		cartRepository.remove(prod);
		
	}

	@Override
	public List<ProductDTO> getAllProducts() {
		return cartRepository.getListOfProducts();
	}

}
