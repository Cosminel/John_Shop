package eu.ubis.eshop.bf.integration.model;

import eu.ubis.eshop.bf.domain.model.User;

public final class UserMapper {
	private UserMapper() {
		
	}
	
	public static User enitityToModel(UserEntity entity)
	{
		User model = new User();
		model.setAddress(entity.getAddress());
		model.setEmail(entity.getEmail());
		model.setFirstName(entity.getFirstName());
		model.setId(entity.getId());
		model.setName(entity.getName());
		model.setPassword(entity.getPassword());
		model.setUser(entity.getUser());
		model.setRole(entity.getRole());
		return model;
	}
	
	public static UserEntity modelToEntity(User model)
	{
		UserEntity entity = new UserEntity();
		entity.setAddress(model.getAddress());
		entity.setEmail(model.getEmail());
		entity.setFirstName(model.getFirstName());
		entity.setId(model.getId());
		entity.setName(model.getName());
		entity.setPassword(model.getPassword());
		entity.setUser(model.getUser());
		entity.setRole(model.getRole());
		return entity;
		
		
		
	}
}
