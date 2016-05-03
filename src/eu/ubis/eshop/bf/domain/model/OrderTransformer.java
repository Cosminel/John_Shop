package eu.ubis.eshop.bf.domain.model;

import java.util.ArrayList;
import java.util.List;

import eu.ubis.eshop.bfcl.OrderDTO;
import eu.ubis.eshop.bfcl.ProductDTO;

public class OrderTransformer {
	private OrderTransformer() {
	}

	public static Order dtoToModel(OrderDTO dto) {
		List<Product> products = new ArrayList<Product>();
		Order model = new Order();
		model.setId(dto.getId());
		model.setOrderDate(dto.getOrderDate());
		for (ProductDTO product: dto.getProducts())
		{
			products.add(ProductTransformer.dtoToModel(product));
		}
		model.setProducts(products);
		model.setSum(dto.getSum());
		model.setUserId(dto.getUserId());
		return model;
	}

	public static OrderDTO modelToDto(Order model) {
		List<ProductDTO> products = new ArrayList<ProductDTO>();
		OrderDTO dto = new OrderDTO();
		dto.setId(model.getId());
		dto.setOrderDate(model.getOrderDate());
		for (Product product: model.getProducts())
		{
			products.add(ProductTransformer.modelToDto(product));
		}
		dto.setProducts(products);
		dto.setSum(model.getSum());
		dto.setUserId(model.getUserId());
		return dto;
	}
}
