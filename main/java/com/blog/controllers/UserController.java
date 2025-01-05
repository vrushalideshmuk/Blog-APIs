package com.blog.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blog.config.AppConstants;
import com.blog.payload.ApiResponse;
import com.blog.payload.PostResponse;
import com.blog.payload.UserDTO;
import com.blog.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userservice;
	// POST - Create User
	@PostMapping("/")
	public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDto){
		UserDTO createdUserDto = this.userservice.createUser(userDto);
		return new ResponseEntity<>(createdUserDto,HttpStatus.CREATED);
	}
	
	//Put - Update User
	@PutMapping("/{userId}")
	public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDto, @PathVariable("userId") Integer uid){
		UserDTO updatedUser = this.userservice.updateUser(userDto, uid);
		return ResponseEntity.ok(updatedUser);
	}
	
	//Delete - Delete User
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer uid){
		 this.userservice.deleteUser(uid);
		 return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted successfulyy", true), HttpStatus.OK);
	}
	//Get - Get User details
	//Get All Users
	@GetMapping("/")
	public ResponseEntity<PostResponse> getAllUsers(
			@RequestParam(value = "pageNum", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNum,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir){
		PostResponse postResp = this.userservice.getAllUsers(pageNum, pageSize, sortBy, sortDir);
		return new ResponseEntity<PostResponse>(postResp, HttpStatus.OK);
	}
	
	//Get single user by user id
	@GetMapping("/{userId}")
	public ResponseEntity<UserDTO> getSingleUser(@PathVariable Integer userId){
		return ResponseEntity.ok(this.userservice.getUserById(userId));
	}
}
