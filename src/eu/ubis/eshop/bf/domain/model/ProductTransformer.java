package eu.ubis.eshop.bf.domain.model;

import eu.ubis.eshop.bfcl.ProductDTO;

public class ProductTransformer {

	private ProductTransformer() {
	}

	public static Product dtoToModel(ProductDTO product) {
		Product model = new Product();
		model.setName(product.getName());
		model.setDescription(product.getDescription());
		model.setPrice(product.getPrice());
		model.setCategory(product.getCategory());
		model.setSubcategory(product.getSubcategory());
		model.setImagePath(product.getImagePath());
		model.setProductId(product.getProductId());
		model.setQuantity(product.getQuantity());
		return model;
	}

	public static ProductDTO modelToDto(Product model) {
		ProductDTO dto = new ProductDTO();
		dto.setName(model.getName());
		dto.setDescription(model.getDescription());
		dto.setPrice(model.getPrice());
		dto.setCategory(model.getCategory());
		dto.setSubcategory(model.getSubcategory());
		dto.setProductId(model.getProductId());
		dto.setQuantity(model.getQuantity());
		if (model.getImagePath()!=null)
		dto.setImagePath(model.getImagePath());
		else
		dto.setImagePath("http://alexeykobelev.com/wp-content/uploads/2014/01/fructs.jpg");
		return dto;
	}
}
