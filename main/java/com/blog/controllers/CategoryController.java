package com.blog.controllers;

import java.util.List;

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
import com.blog.payload.CategoryDto;
import com.blog.payload.PostResponse;
import com.blog.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	//Create
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto){
		CategoryDto createCategory = this.categoryService.createCategory(categoryDto);
		return new ResponseEntity<>(createCategory,HttpStatus.CREATED);
	}
	
	//Update
	@PutMapping("/{catId}")
	public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto,@PathVariable Integer catId){
		CategoryDto category = this.categoryService.updateCategory(categoryDto, catId);
		return new ResponseEntity<>(category, HttpStatus.OK);
	}
	//Delete
	@DeleteMapping("/{catId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer catId) {
		  this.categoryService.deleteCategory(catId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category deleted successfully",true), HttpStatus.OK );
	}
	
	//Get Single Category
	@GetMapping("/{catId}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer catId){
		CategoryDto categoryDto = this.categoryService.getCategory(catId);
		return new ResponseEntity<>(categoryDto, HttpStatus.FOUND);
	}
	
	//Get All Categories
	@GetMapping("/")
	public ResponseEntity<PostResponse> getCategories(
			@RequestParam(value = "pageNum", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNum,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = "categoryid", required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir){
			
		PostResponse postResp = this.categoryService.getAllCategory(pageNum, pageSize, sortBy, sortDir);
		return new ResponseEntity<PostResponse>(postResp, HttpStatus.OK);
	}
}
