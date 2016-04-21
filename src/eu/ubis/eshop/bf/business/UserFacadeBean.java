package eu.ubis.eshop.bf.business;

import eu.ubis.eshop.bf.domain.model.User;
import eu.ubis.eshop.bf.domain.repo.UserRepositoryBean;
import eu.ubis.eshop.bfcl.UserDTO;
import eu.ubis.eshop.bfcl.UserFacade;
import eu.ubis.eshop.bf.domain.model.UserTransformer;

public class UserFacadeBean implements UserFacade {
	private static final UserRepositoryBean userRepository = new UserRepositoryBean();
	
	@Override
	public void loginUser(String userName, String password) {

		userRepository.loginUser(userName, password);

	}

	@Override
	public void logOutUser(int userId) {
		userRepository.LogOutUser();
		
	}
	

	@Override
	public UserDTO getUserByUserName(String userName) {
		//TO DO
		return null;
	}

	@Override
	public UserDTO getUserById(int userID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void editAccount(int userId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAccount(int userId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createUser(UserDTO user) {
		User model = new User();
		model = UserTransformer.dtoToModel(user);
		userRepository.createUser(model);
		
	}

	@Override
	public UserDTO getCurrentUser() {
		User model = new User();
		UserDTO dto = new UserDTO();
		model = UserRepositoryBean.getCurrentUser();
		if (model.getUser()!=null)
		dto = UserTransformer.modelToDTO(model);
		else
		dto = null;
		return dto;
		
	}

	
}
