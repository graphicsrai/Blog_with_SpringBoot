package com.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.dto.ApiResponse;
import com.blog.dto.CategoryDto;
import com.blog.service.CategoryService;

import jakarta.validation.Valid;

@RestController
@Validated
@RequestMapping("/api/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	//Create
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto)
	{
		CategoryDto createdCategoryDto=this.categoryService.createCategory(categoryDto);
		return new ResponseEntity<>(createdCategoryDto,HttpStatus.CREATED);
	}
	//update
	@PutMapping("/{id}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,@PathVariable Integer id)
	{
		CategoryDto updatedCategoryDto=this.categoryService.updateCategory(categoryDto, id);
		return new ResponseEntity<CategoryDto>(updatedCategoryDto,HttpStatus.OK);
	}
	
	//delete
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer id)
	{
		this.categoryService.deleteCategory(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category has deleted sussessfully", true),HttpStatus.OK);
	}
	
	//get
	@GetMapping("/{id}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer id)
	{
		CategoryDto catDto=this.categoryService.getCategory(id);
		return new ResponseEntity<CategoryDto>(catDto,HttpStatus.OK);
	}
	//getAll
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getCategorys()
	{
		List<CategoryDto> catDto=this.categoryService.getCategorys();
		return new ResponseEntity<List<CategoryDto>>(catDto,HttpStatus.OK);
	}
}
