package eu.ubis.eshop.bfcl;

public interface UserFacade {
	public void loginUser(String userName,String password);
	public void logOutUser(int userId);
	public UserDTO getUserByUserName(String userName);
	public UserDTO getUserById(int userID);
	public void editAccount(int userId);
	public void deleteAccount(int userId);
	public void createUser(UserDTO user);
	public UserDTO getCurrentUser();
}
