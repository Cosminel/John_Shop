package eu.ubis.eshop.bf.integration.model;

import java.util.ArrayList;
import java.util.List;
import eu.ubis.eshop.bf.domain.model.Order;
import eu.ubis.eshop.bf.domain.model.Product;

public class OrderMapper {

	private OrderMapper(){
		
	}
	public static Order enitityToModel(OrdersEntity entity)
	{	List<Product> products = new ArrayList<Product>();
		Order model = new Order();
		model.setId(entity.getId());
		model.setOrderDate(entity.getOrderDate());
		for (ProductEntity product: entity.getProducts())
		{
			products.add(ProductMapper.entityToModel(product));
		}
		model.setProducts(products);
		model.setSum(entity.getSum());
		model.setUserId(entity.getUserId());
		return model;
	}
	
	public static OrdersEntity modelToEntity(Order model)
	{	List<ProductEntity> products = new ArrayList<ProductEntity>();
		OrdersEntity entity = new OrdersEntity();
		entity.setId(model.getId());
		entity.setOrderDate(model.getOrderDate());
		for (Product product: model.getProducts())
		{
			products.add(ProductMapper.modelToEnt(product));
		}
		entity.setProducts(products);
		entity.setSum(model.getSum());
		entity.setUserId(model.getUserId());
		return entity;
	
	}
}
