package eu.ubis.eshop.bfcl;

import java.util.List;

import eu.ubis.eshop.bf.domain.model.Order;

public interface OrdersFacade {

	void addOrder(OrderDTO dto);
	public List<OrderDTO> getAllOrdersByUserId(int id);
}
