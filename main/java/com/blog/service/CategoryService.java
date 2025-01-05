package com.blog.service;

import java.util.List;


import com.blog.payload.CategoryDto;
import com.blog.payload.PostResponse;

public interface CategoryService {

	//Create 
	CategoryDto createCategory(CategoryDto categoryDto);
	
	//Update
	CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);
	
	//delete
	void deleteCategory(Integer CategoryId);
	
	//getSingle
	CategoryDto getCategory(Integer CategoryId);
	
	//getAll
	PostResponse getAllCategory(Integer pageNum, Integer pageSize, String sortBy, String sortDir);
}
