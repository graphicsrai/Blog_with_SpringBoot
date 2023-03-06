package com.blog.service;

import java.util.List;

import com.blog.dto.PostDto;
import com.blog.entity.Post;

public interface PostService {
	
	//create
	PostDto createPost(PostDto postDto,Integer UserId,Integer categoryId);
	
	//update
	Post updatePost(PostDto postDto, Integer PostId);
	
	//Delete
	void deletePost(Integer postId);
	
	//get Post BY Id
	Post getPost(Integer postId);
	
	//getPosts
	List<Post> getPosts();
	
	//get all post by category
	List<Post> getPostByCategory(Integer categoryId);
	
	//get all posts by user
	List<Post> getPostByUser(Integer userId);
	
	//Search Post
	List<Post> searchPost(String keyword);
	
}
