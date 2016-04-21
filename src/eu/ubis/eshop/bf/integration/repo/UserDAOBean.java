package eu.ubis.eshop.bf.integration.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import eu.ubis.eshop.bf.integration.model.UserEntity;

public class UserDAOBean {
	private static final String GET_USER_BY_ID = "SELECT * FROM user ORDER BY id=?";
	private static final String CREATE_USER = "INSERT INTO \"USER\" (\"NAME\",firstname,address,\"USER\",password,email) values (?,?,?,?,?,?)";
	private static final String GET_USER_BY_NAME_PASSWORD = "SELECT * FROM \"USER\" WHERE \"USER\"=? and password=?";
	
	public UserEntity getUserById(int userId)
	{
		Connection con = ConnectionHelperClass.getMysqlConnection();
		//List<UserEntity> userList = new ArrayList<UserEntity>();
		UserEntity user = new UserEntity();
		try {
			PreparedStatement prepareStatement = con.prepareStatement(GET_USER_BY_ID);
			prepareStatement.setInt(1, userId);
			ResultSet resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				
				user.setId(resultSet.getInt("id"));
				user.setName(resultSet.getString("name"));
				user.setFirstName(resultSet.getString("firstname"));
				user.setAddress(resultSet.getString("adress"));
				user.setUser(resultSet.getString("user"));
				user.setEmail(resultSet.getString("email"));
		//				userList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public UserEntity getUserByNamePassword(String userName, String password)
	{
		Connection con = ConnectionHelperClass.getMysqlConnection();
		UserEntity user = new UserEntity();
		
		try {
			PreparedStatement prepareStatement = con.prepareStatement(GET_USER_BY_NAME_PASSWORD);
			prepareStatement.setString(1, userName);
			prepareStatement.setString(2,password);
			ResultSet resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				
				user.setId(resultSet.getInt("id"));
				user.setName(resultSet.getString("name"));
				user.setFirstName(resultSet.getString("firstname"));
				user.setAddress(resultSet.getString("address"));
				user.setUser(resultSet.getString("user"));
				user.setEmail(resultSet.getString("email"));
				user.setPassword(resultSet.getString("password"));
				user.setRole(resultSet.getInt("role"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	
	public void updateUser(UserEntity user)
	{
		//TODO
	}
	
	public void deleteUser(int userID)
	{
		//TODO
	}
	
	public void createUser(UserEntity user)
	{	Connection con = ConnectionHelperClass.getMysqlConnection();
		
		PreparedStatement prepareStatement;
		try {
			prepareStatement = con.prepareStatement(CREATE_USER);
			prepareStatement.setString(1, user.getName());
			prepareStatement.setString(2, user.getFirstName());
			prepareStatement.setString(3, user.getAddress());
			prepareStatement.setString(4, user.getUser());
			prepareStatement.setString(5, user.getPassword());
			prepareStatement.setString(6, user.getEmail());
			prepareStatement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
}
