package eu.ubis.eshop.bf.domain.repo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import eu.ubis.eshop.bf.domain.model.Order;
import eu.ubis.eshop.bf.integration.model.OrderMapper;
import eu.ubis.eshop.bf.integration.model.OrdersEntity;
import eu.ubis.eshop.bf.integration.repo.OrdersDAOBean;

public class OrderRepositoryBean {
	private static final OrdersDAOBean orderDAOBean = new OrdersDAOBean();
	
	public void addOrder(Order model) throws SQLException
	{	OrdersEntity entity = OrderMapper.modelToEntity(model);
		orderDAOBean.addOrder(entity);
	}
	
	public List<Order> getAllOrdersByUserId(int id) throws SQLException
	{
		List<OrdersEntity> userEntityOrders = orderDAOBean.getAllOrdersByUserId(id);
		List<Order> userModelOrders = new ArrayList<Order>();
		for (OrdersEntity entity : userEntityOrders)
		{
			userModelOrders.add(OrderMapper.enitityToModel(entity));
		}
		
		return userModelOrders;
	}
	
	
	
}
