package eu.ubis.eshop.bfcl;

import java.sql.Date;
import java.util.List;

import eu.ubis.eshop.bf.integration.model.ProductEntity;
import oracle.sql.DATE;

public class OrderDTO {
	private int id ;
	private int userId;
	private float sum;
	private Date orderDate;
	private List<ProductDTO> products;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public float getSum() {
		return sum;
	}
	public void setSum(float sum) {
		this.sum = sum;
	}
	public List<ProductDTO> getProducts() {
		return products;
	}
	public void setProducts(List<ProductDTO> products) {
		this.products = products;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
		System.out.println("dasda");
	}
}
