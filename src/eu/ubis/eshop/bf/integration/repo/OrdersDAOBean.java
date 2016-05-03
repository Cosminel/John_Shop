package eu.ubis.eshop.bf.integration.repo;


import java.math.BigDecimal;
import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Struct;
import java.util.ArrayList;
import java.util.List;

import eu.ubis.eshop.bf.integration.model.OrdersEntity;
import eu.ubis.eshop.bf.integration.model.ProductEntity;


public class OrdersDAOBean {
	private static final String INSERT_ORDER= "{call add_order(?,?)}";
	private static final String GET_ORDERS_BY_USER_ID = "SELECT * FROM orders where USER_ID=?";
	public void addOrder(OrdersEntity entity) throws SQLException {

		Connection con = ConnectionHelperClass.getMysqlConnection();
		CallableStatement stmt = con.prepareCall(INSERT_ORDER);
		Object[] pArray = new Object[6];
		Struct[] struct = new Struct[entity.getProducts().size()];
		
		int arrayIndex = 0;
		for (ProductEntity product: entity.getProducts())
		{
			pArray[0] = product.getProductId();
			pArray[1] = product.getName();
			pArray[2] = product.getDescription();
			pArray[3] = product.getPrice();
			pArray[4] = product.getImagePath();
			pArray[5] = product.getQuantity();
			
			struct[arrayIndex++] = con.createStruct("OBJ_PRODUCT", pArray);
		}
		Array productArray = ((oracle.jdbc.OracleConnection)con).createOracleArray("ARRAY_PRODUCT",struct );
		
		stmt.setInt(1, entity.getUserId());
		stmt.setArray(2, productArray);
		stmt.execute();
		
	}
	
	public List<OrdersEntity> getAllOrdersByUserId(int id) throws SQLException
	{
		Connection con = ConnectionHelperClass.getMysqlConnection();
		List<OrdersEntity> orders = new ArrayList<OrdersEntity>();
		
		PreparedStatement prepareStatement = con.prepareStatement(GET_ORDERS_BY_USER_ID);
		prepareStatement.setInt(1, id);
		ResultSet rs = prepareStatement.executeQuery();
		while(rs.next())
		{	List<ProductEntity> products = new ArrayList<ProductEntity>() ;
			OrdersEntity entity = new OrdersEntity();
			entity.setId(rs.getInt("id"));
			entity.setOrderDate(rs.getDate("order_date"));
			entity.setSum(rs.getFloat("sum"));
			entity.setUserId(rs.getInt("user_id"));
			Array array = rs.getArray("products");
			Object[] product_list = (Object[]) array.getArray();
			for (int i = 0; i < product_list.length; i++) 
			{  
                ProductEntity nestedProduct = new ProductEntity();
				Struct address = (Struct) product_list[i];  
                Object[] attrib = address.getAttributes();
                BigDecimal id1 = (BigDecimal) attrib[0];
                nestedProduct.setProductId(id1.intValue());
                nestedProduct.setName(attrib[1].toString());
                nestedProduct.setDescription(attrib[2].toString());
                BigDecimal price1 = (BigDecimal) attrib[3];
                nestedProduct.setPrice(price1.floatValue());
                nestedProduct.setImagePath(attrib[4].toString());
                BigDecimal q1 = (BigDecimal)attrib[5];
                nestedProduct.setQuantity(q1.intValue());
                
                products.add(nestedProduct);
             }  
			entity.setProducts(products);
			orders.add(entity);
		}
		return orders;
	}
	
	
}
