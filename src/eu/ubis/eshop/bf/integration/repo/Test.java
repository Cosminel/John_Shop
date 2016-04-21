package eu.ubis.eshop.bf.integration.repo;

import java.sql.Connection;

import eu.ubis.eshop.bf.integration.model.UserEntity;

public class Test {

	public static void main(String[] args) throws ClassNotFoundException {
		
		UserEntity user = new UserEntity();
		user.setFirstName("cucos");
		user.setName("adrian");
		user.setUser("adrian.cucos");
		
		UserDAOBean  bean = new UserDAOBean();
		bean.createUser(user);
		
	}

}
