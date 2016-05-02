package eu.ubis.eshop.bfcl;

import java.util.List;

public interface ProductFacade {

	public List<ProductDTO> getAllProducts();

	public List<String> getAllCategories();

	public List<String> getAllSubcategories();
	
	public List<String> getSubcategoriesByCategoryName(String categoryName);
	
	public ProductDTO getProductbyId(int ProductId);

	public void saveProduct(ProductDTO product);

}
