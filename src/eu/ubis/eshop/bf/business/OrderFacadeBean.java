package eu.ubis.eshop.bf.business;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import eu.ubis.eshop.bf.domain.model.Order;
import eu.ubis.eshop.bf.domain.model.OrderTransformer;
import eu.ubis.eshop.bf.domain.repo.OrderRepositoryBean;
import eu.ubis.eshop.bfcl.OrderDTO;
import eu.ubis.eshop.bfcl.OrdersFacade;

public class OrderFacadeBean implements OrdersFacade {
	private static final OrderRepositoryBean orderRepository = new OrderRepositoryBean();
	@Override
	public void addOrder(OrderDTO dto){
		Order model = new Order();
		model = OrderTransformer.dtoToModel(dto);
		try {
			orderRepository.addOrder(model);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Override
	public List<OrderDTO> getAllOrdersByUserId(int id) {
		List<OrderDTO> ordersDTO = new ArrayList<OrderDTO>();
		List<Order> ordersModel;
		try {
			ordersModel = orderRepository.getAllOrdersByUserId(id);
			for (Order o:ordersModel)
			{
				ordersDTO.add(OrderTransformer.modelToDto(o));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return ordersDTO;
	}

}
