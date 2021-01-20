package com.uno.user.service;


import com.uno.user.dto.UserDTO;

import java.util.List;

public interface UserService {

	public UserDTO addUser(UserDTO user);
	public UserDTO updateUser(UserDTO user, Integer userId);
	public UserDTO getUser(Integer userId);
	public List<UserDTO> findAll();
}
