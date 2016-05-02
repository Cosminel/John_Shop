package eu.ubis.eshop.bf.integration.model;

import eu.ubis.eshop.bf.domain.model.Product;

public final class Mapper {

	private Mapper() {
	}

	public static Product entityToModel(ProductEntity entity) {
		Product model = new Product();
		model.setProductId(entity.getProductId());
		model.setName(entity.getName());
		model.setDescription(entity.getDescription());
		model.setPrice(entity.getPrice());
		model.setImagePath(entity.getImagePath());
		model.setQuantity(entity.getQuantity());
		return model;
	}

	public static ProductEntity modelToEnt(Product model) {
		ProductEntity entity = new ProductEntity();
		entity.setProductId(model.getProductId());
		entity.setName(model.getName());
		entity.setDescription(model.getDescription());
		entity.setPrice(model.getPrice());
		entity.setImagePath(model.getImagePath());
		entity.setQuantity(model.getQuantity());
		return entity;
	}

}
