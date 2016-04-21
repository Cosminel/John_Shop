package eu.ubis.eshop.bf.domain.model;

import eu.ubis.eshop.bfcl.UserDTO;

public final class UserTransformer {

	private UserTransformer(){
		
	}
	
	public static UserDTO modelToDTO(User model)
	{
		UserDTO dto = new UserDTO();
		dto.setAddress(model.getAddress());
		dto.setEmail(model.getEmail());
		dto.setFirstName(model.getFirstName());
		dto.setUser(model.getUser());
		dto.setPassword(model.getPassword());
		dto.setName(model.getName());
		dto.setRole(1);
		return dto;
	}
	
	public static User dtoToModel (UserDTO dto)
	{
		User model = new User();
		model.setAddress(dto.getAddress());
		model.setEmail(dto.getEmail());
		model.setFirstName(dto.getFirstName());
		model.setName(dto.getName());
		model.setPassword(dto.getPassword());
		model.setUser(dto.getUser());
		
		return model;
	}
	
}
