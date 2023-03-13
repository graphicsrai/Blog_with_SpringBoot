package com.blog.service;

import java.util.List;

import com.blog.dto.PostDto;
import com.blog.dto.PostResponse;


public interface PostService {
	
	//create
	PostDto createPost(PostDto postDto,Integer UserId,Integer categoryId);
	
	//update
	PostDto updatePost(PostDto postDto, Integer PostId);
	
	//Delete
	void deletePost(Integer postId);
	
	//get Post BY Id
	PostDto getPost(Integer postId);
	
	//getPosts
	PostResponse getPosts(Integer pageNumber,Integer pageSize,String sortBy,String sortDir);
	
	//get all post by category
	List<PostDto> getPostByCategory(Integer categoryId);
	
	//get all posts by user
	List<PostDto> getPostByUser(Integer userId);
	
	//Search Post
	List<PostDto> searchPost(String keyword);
	
}
