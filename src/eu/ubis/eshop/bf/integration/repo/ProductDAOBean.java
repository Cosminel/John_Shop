package eu.ubis.eshop.bf.integration.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import eu.ubis.eshop.bf.integration.model.ProductEntity;
import eu.ubis.eshop.bfcl.ProductDTO;

public class ProductDAOBean {
	private static final String GET_ALL_PRODUCTS = "SELECT * FROM product ORDER BY id";

	private static final String CATEGORY_SELECT = "SELECT * FROM category WHERE id=?";
	private static final String CATEGORY_SELECT_BY_NAME = "SELECT * FROM category WHERE name=?";
	private static final String CATEGORY_SELECT_ALL = "SELECT * FROM category";
	
	private static final String SUBCATEGORY_SELECT = "SELECT * FROM subcategory WHERE id=?";
	private static final String SUBCATEGORY_SELECT_BY_NAME = "SELECT * FROM subcategory WHERE name=?";
	private static final String SUBCATEGORY_SELECT_ALL = "SELECT * FROM subcategory";
	private static final String SUBCATEGORY_SELECT_BY_CATEGORY_ID = "SELECT * FROM subcategory WHERE category_id=?";
	
	private static final String PRODUCT_INSERT = "INSERT INTO product ( category_id, subcategory_id, name, description, price, image_path,quantity) VALUES ( ?, ?, ?, ?, ?, '',?)";
	private static final String GET_PRODUCT_BY_ID ="SELECT * FROM product WHERE id = ? ";


	public List<ProductEntity> getAllProducts() {

		Connection con = ConnectionHelperClass.getMysqlConnection();
		List<ProductEntity> productList = new ArrayList<ProductEntity>();

		try {
			ResultSet resultSet = con.createStatement().executeQuery(GET_ALL_PRODUCTS);
			while (resultSet.next()) {
				ProductEntity product = new ProductEntity();
				product.setProductId(resultSet.getInt("id"));
				product.setName(resultSet.getString("name"));
				product.setCategory(resultSet.getInt("category_id"));
				product.setSubcategory(resultSet.getInt("subcategory_id"));
				product.setPrice(resultSet.getFloat("price"));
				product.setDescription(resultSet.getString("description"));
				product.setImagePath(resultSet.getString("image_path"));
				product.setQuantity(resultSet.getInt("quantity"));
				productList.add(product);
			}
			resultSet.close();
			
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return productList;
	}

	public List<String> getAllCategories() {
		Connection con = ConnectionHelperClass.getMysqlConnection();
		List<String> categories = new ArrayList<String>();
		try {
			PreparedStatement prepareStatement = con.prepareStatement(CATEGORY_SELECT_ALL);
			ResultSet resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				categories.add(resultSet.getString("name"));
			}
			prepareStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return categories;
	}

	public List<String> getAllSubcategories() {
		Connection con = ConnectionHelperClass.getMysqlConnection();
		List<String> subcategories = new ArrayList<String>();
		try {
			PreparedStatement prepareStatement = con.prepareStatement(SUBCATEGORY_SELECT_ALL);
			ResultSet resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				subcategories.add(resultSet.getString("name"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return subcategories;
	}

	public String getCategoryById(int id) {
		Connection con = ConnectionHelperClass.getMysqlConnection();
		try {
			PreparedStatement prepareStatement = con.prepareStatement(CATEGORY_SELECT);
			prepareStatement.setInt(1, id);
			ResultSet resultSet = prepareStatement.executeQuery();
			resultSet.next();
			String name = resultSet.getString("name");
			prepareStatement.close();
			return name;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int getCategoryIdByName(String name) {
		Connection con = ConnectionHelperClass.getMysqlConnection();
		try {
			PreparedStatement prepareStatement = con.prepareStatement(CATEGORY_SELECT_BY_NAME);
			prepareStatement.setString(1, name);
			int id=-1;
			ResultSet resultSet = prepareStatement.executeQuery();
			if (resultSet.next())
				 id = resultSet.getInt("id");
			prepareStatement.close();
			return id;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public String getSubCategoryById(int id) {
		Connection con = ConnectionHelperClass.getMysqlConnection();
		try {
			PreparedStatement prepareStatement = con.prepareStatement(SUBCATEGORY_SELECT);
			prepareStatement.setInt(1, id);
			ResultSet resultSet = prepareStatement.executeQuery();
			resultSet.next();
			return resultSet.getString("name");

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public int getSubcategoryIdByName(String name) {
		Connection con = ConnectionHelperClass.getMysqlConnection();
		try {
			PreparedStatement prepareStatement = con.prepareStatement(SUBCATEGORY_SELECT_BY_NAME);
			prepareStatement.setString(1, name);
			ResultSet resultSet = prepareStatement.executeQuery();
			resultSet.next();
			int id = resultSet.getInt("id");
			prepareStatement.close();
			return id;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public List<String> getSubcategoriesByCategoryName(String categoryName) {
		Connection con = ConnectionHelperClass.getMysqlConnection();
		List<String> subcategories = new ArrayList<String>();
		
		try {
			int categoryID = getCategoryIdByName(categoryName);
			PreparedStatement prepareStatement = con.prepareStatement(SUBCATEGORY_SELECT_BY_CATEGORY_ID);
			prepareStatement.setInt(1, categoryID);
			ResultSet resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				subcategories.add(resultSet.getString("name"));
			}
			prepareStatement.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return subcategories;
	}

	public void addProduct(ProductEntity product) {
		Connection con = ConnectionHelperClass.getMysqlConnection();
		try {
			PreparedStatement prepareStatement = con.prepareStatement(PRODUCT_INSERT);
			prepareStatement.setInt(1, product.getCategory());
			prepareStatement.setInt(2, product.getSubcategory());
			prepareStatement.setString(3, product.getName());
			prepareStatement.setString(4, product.getDescription());
			prepareStatement.setFloat(5, product.getPrice());
			prepareStatement.setInt(6, product.getQuantity());
			prepareStatement.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public ProductEntity getProductbyId(int productId) {
		Connection con = ConnectionHelperClass.getMysqlConnection();
		ProductEntity product=new ProductEntity();
		try {
			PreparedStatement prepareStatement = con.prepareStatement(GET_PRODUCT_BY_ID);
			prepareStatement.setInt(1, productId);
			ResultSet resultSet = prepareStatement.executeQuery();
			if (resultSet.next())
			{
				product.setProductId(resultSet.getInt("id"));
				product.setName(resultSet.getString("name"));
				product.setCategory(resultSet.getInt("category_id"));
				product.setSubcategory(resultSet.getInt("subcategory_id"));
				product.setPrice(resultSet.getFloat("price"));
				product.setDescription(resultSet.getString("description"));
				product.setImagePath(resultSet.getString("image_path"));
				product.setQuantity(resultSet.getInt("quantity"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return product;
		}

}
