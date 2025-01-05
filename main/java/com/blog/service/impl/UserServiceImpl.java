	package com.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blog.exceptions.*;
import com.blog.entities.Category;
import com.blog.entities.Users;
import com.blog.payload.PostDto;
import com.blog.payload.PostResponse;
import com.blog.payload.UserDTO;
import com.blog.repositories.UserRepo;
import com.blog.service.UserService;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo UserRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public UserDTO createUser(UserDTO userDto) {
		Users user = this.dtoToUser(userDto); 
		Users savedUser = this.UserRepo.save(user);
		return this.userToDTO(savedUser);
	}

	@Override
	public UserDTO updateUser(UserDTO userDTO, Integer userId) {
		Users user = this.UserRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
		user.setId(userDTO.getId());
		user.setEmail(userDTO.getEmail());
		user.setName(userDTO.getName());
		user.setPassword(userDTO.getPassword());
		user.setAbout(userDTO.getAbout());
		Users updatedUser = this.UserRepo.save(user);
		UserDTO userDto1 = this.userToDTO(updatedUser);
		return userDto1;
	}

	@Override
	public UserDTO getUserById(Integer userId) {
		Users user = this.UserRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
		return this.userToDTO(user);
	}

	@Override
	public PostResponse getAllUsers(Integer pageNum, Integer pageSize, String sortBy, String sortDir) {
		
		Sort sort = (sortDir.equalsIgnoreCase("asc"))?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
		Pageable p = PageRequest.of(pageNum, pageSize, sort);
		Page<Users> pageUser = this.UserRepo.findAll(p);
		List<Users> allUsers = pageUser.getContent();
		
		List<UserDTO> userDtos = allUsers.stream().map((Users) -> this.modelMapper.map(Users, UserDTO.class)).collect(Collectors.toList());
		
		PostResponse<UserDTO> postResp = new PostResponse();
		postResp.setContent(userDtos);
		postResp.setPageNum(pageUser.getNumber());
		postResp.setPageSize(pageUser.getSize());
		postResp.setTotalElements(pageUser.getTotalElements());
		postResp.setTotalPages(pageUser.getTotalPages());
		postResp.setLastPage(pageUser.isLast());

		return postResp;
	}

	@Override
	public void deleteUser(Integer userId) {
		Users user = this.UserRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
		this.UserRepo.delete(user);
	}
	
	private Users dtoToUser(UserDTO userDTO) {
		
		Users user = this.modelMapper.map(userDTO, Users.class);
		
//		user.setId(userDTO.getId());
//		user.setEmail(userDTO.getEmail());
//		user.setName(userDTO.getName());
//		user.setPassword(userDTO.getPassword());
//		user.setAbout(userDTO.getAbout());
		return user;
	}
	
	public UserDTO userToDTO(Users user) {
		UserDTO userDto = this.modelMapper.map(user, UserDTO.class);
		return userDto;
	}

}
