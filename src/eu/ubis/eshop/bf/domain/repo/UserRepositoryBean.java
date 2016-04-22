package eu.ubis.eshop.bf.domain.repo;

import eu.ubis.eshop.bf.domain.model.User;
import eu.ubis.eshop.bf.integration.model.UserEntity;
import eu.ubis.eshop.bf.integration.model.UserMapper;
import eu.ubis.eshop.bf.integration.repo.UserDAOBean;

public class UserRepositoryBean {
	private static final UserDAOBean userDAOBean = new UserDAOBean();
	private static User currentUser=null;
	public void loginUser(String userName,String password)
	{	
		UserEntity entity = new UserEntity();
		entity = userDAOBean.getUserByNamePassword(userName, password);
		User user  = new User();
		user = UserMapper.enitityToModel(entity);
		
		UserRepositoryBean.currentUser = user;
	}
	public void LogOutUser()
	{
		UserRepositoryBean.currentUser=null;
	}
	public static User getCurrentUser() {
		return currentUser;
	}
	
	public  void createUser(User model)
	{
		UserEntity entity = UserMapper.modelToEntity(model);
		userDAOBean.createUser(entity);
		
	}
	
	public void editAccount(User model)
	{
		UserEntity entity = UserMapper.modelToEntity(model);
		userDAOBean.updateUser(entity);
	}
	
	
}
