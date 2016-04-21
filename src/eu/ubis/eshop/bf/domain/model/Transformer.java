package eu.ubis.eshop.bf.domain.model;

import eu.ubis.eshop.bfcl.ProductDTO;

public class Transformer {

	private Transformer() {
	}

	public static Product dtoToModel(ProductDTO dto) {
		Product model = new Product();
		model.setName(dto.getName());
		model.setDescription(dto.getDescription());
		model.setPrice(dto.getPrice());
		model.setCategory(dto.getCategory());
		model.setSubcategory(dto.getSubcategory());
		model.setImagePath(dto.getImagePath());
		return model;
	}

	public static ProductDTO modelToDto(Product model) {
		ProductDTO dto = new ProductDTO();
		dto.setName(model.getName());
		dto.setDescription(model.getDescription());
		dto.setPrice(model.getPrice());
		dto.setCategory(model.getCategory());
		dto.setSubcategory(model.getSubcategory());
		if (model.getImagePath()!=null)
		dto.setImagePath(model.getImagePath());
		else
		dto.setImagePath("http://alexeykobelev.com/wp-content/uploads/2014/01/fructs.jpg");
		return dto;
	}
}
