package com.blog.service;

import java.util.List;

import com.blog.entities.Post;
import com.blog.payload.PostDto;
import com.blog.payload.PostResponse;

public interface PostService {

	//create 
	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
	
	//update 
	PostDto updatePost(PostDto postDto, Integer postId);
	
	//delete
	void deletePost(Integer postId);
	
	//get all posts
	PostResponse getAllPost(Integer pageNum, Integer pageSize, String sortBy, String sortDir);
	
	//get post
	PostDto getPostById(Integer postId);
	
	//get all post by category
	List<PostDto> getPostsByCategory(Integer categoryId);
	
	//get all posts by user
	List<PostDto> getPostsByUser(Integer userId);
	
	//search posts
	List<PostDto> searchPosts(String keyword);
}
