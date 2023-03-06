package com.blog.service.impl;

import java.util.Date;
import java.util.List;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.dto.PostDto;
import com.blog.entity.Category;
import com.blog.entity.Post;
import com.blog.entity.User;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.repository.CategoryRepo;
import com.blog.repository.PostRepo;
import com.blog.repository.UserRepo;
import com.blog.service.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Override
	public PostDto createPost(PostDto postDto, Integer categoryId, Integer UserId) {
		
		User user=this.userRepo.findById(UserId).orElseThrow(()-> new ResourceNotFoundException("User","UserId", UserId));
		
		Category category=this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "CAtegory Id", categoryId));
		
		Post post =this.modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		
		Post newPost=this.postRepo.save(post);
		
		return this.modelMapper.map(newPost,PostDto.class);
	}

	@Override
	public Post updatePost(PostDto postDto, Integer PostId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletePost(Integer postId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Post getPost(Integer postId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Post> getPosts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Post> getPostByCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Post> getPostByUser(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Post> searchPost(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
