package com.blog.service;

import java.util.List;

import com.blog.payload.PostResponse;
import com.blog.payload.UserDTO;

public interface UserService {

	UserDTO createUser(UserDTO user);
	
	UserDTO updateUser(UserDTO user, Integer userId);
	
	UserDTO getUserById(Integer userId);
	
	PostResponse getAllUsers(Integer pageNum, Integer pageSize, String sortBy, String sortDir);
	
	void deleteUser(Integer userId);
	
}
