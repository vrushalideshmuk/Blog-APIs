package com.blog.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
import org.springframework.web.multipart.MultipartFile;

import com.blog.config.AppConstants;
import com.blog.payload.ApiResponse;
import com.blog.payload.ImageResponse;
import com.blog.payload.PostDto;
import com.blog.payload.PostResponse;
import com.blog.service.FileService;
import com.blog.service.PostService;
import com.fasterxml.jackson.core.io.IOContext;

@RestController
@RequestMapping("/api/posts")
public class PostController {
	
	@Value("${project.image}")
		private String path;
	//create

	@Autowired
	private FileService fileService;
	
	@Autowired
	private PostService postService;
	
	@PostMapping("/user/{userId}/category/{categoryId}")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,
			@PathVariable Integer userId,@PathVariable Integer categoryId){
		
		PostDto	createdPost = this.postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(createdPost,HttpStatus.CREATED);
	}
	
	@GetMapping("/")
	public ResponseEntity<PostResponse> getAllPost(
			@RequestParam(value = "pageNum", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNum,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = "postId", required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir){
		PostResponse postResp = this.postService.getAllPost(pageNum, pageSize, sortBy, sortDir);
		return new ResponseEntity<PostResponse>(postResp, HttpStatus.OK);
	}
	
	//Get by user
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId){
		
		List<PostDto> allPosts = this.postService.getPostsByUser(userId);
		return new ResponseEntity<List<PostDto>>(allPosts, HttpStatus.OK);
	}
	
	//Get by category
	@GetMapping("/category/{categoryId}")
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId){
		
		List<PostDto> allPosts = this.postService.getPostsByCategory(categoryId);
		return new ResponseEntity<List<PostDto>>(allPosts, HttpStatus.OK);
	}
	
	@DeleteMapping("/{postId}")
	public ApiResponse deletePost(@PathVariable Integer postId) {
		this.postService.deletePost(postId);
		return new ApiResponse("Post is successfully deleted", true);
	}
	
	@PutMapping("/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId){
		this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(postDto, HttpStatus.OK);
	}
	
	@GetMapping("/search/{keywords}")
	public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable("keywords") String keywords){
		List<PostDto> result = this.postService.searchPosts(keywords);
		return new ResponseEntity<List<PostDto>>(result, HttpStatus.OK);
	}
	
	@PostMapping("/post/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadPostImage(
			@RequestParam("image") MultipartFile image,
			@PathVariable Integer postId
			) throws IOException{
		
		String fileName  = this.fileService.uploadImage(path, image);
		PostDto postDto = this.postService.getPostById(postId);
		postDto.setImageName(fileName);
		PostDto updatedPost = this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatedPost, HttpStatus.OK);
		
	}
}
