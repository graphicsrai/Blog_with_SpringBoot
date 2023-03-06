package com.blog.service.impl;

import java.text.Collator;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.dto.CategoryDto;
import com.blog.entity.Category;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.repository.CategoryRepo;
import com.blog.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category cat=this.modelMapper.map(categoryDto, Category.class);
		Category addedCat=this.categoryRepo.save(cat);
		return this.modelMapper.map(addedCat,CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Category Id",categoryId));
		cat.setCatTitle(categoryDto.getCatTitle());
		cat.setCatDesc(categoryDto.getCatDesc());
		Category updatedCat=this.categoryRepo.save(cat);
		return this.modelMapper.map(updatedCat, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id",categoryId));
		this.categoryRepo.delete(cat);	
	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
		Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Category Id",categoryId));
		return this.modelMapper.map(cat, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getCategorys() {
		List<Category> cat=this.categoryRepo.findAll();
		List<CategoryDto> catDtos=cat.stream().map((ca)-> this.modelMapper.map(ca, CategoryDto.class)).collect(Collectors.toList());
		return catDtos;
	}

}
