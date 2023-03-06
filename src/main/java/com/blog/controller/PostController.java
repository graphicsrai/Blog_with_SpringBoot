package com.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.blog.dto.PostDto;
import com.blog.service.PostService;

@RestController
public class PostController {
	
	@Autowired
	private PostService postService;
	
	
	//Create
	@PostMapping("/user/{userId}/category/{catId}/post")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto post, @PathVariable Integer userId, @PathVariable Integer catId)
	{
		PostDto createdPost=this.postService.createPost(post, catId, userId);
		return new ResponseEntity<PostDto>(createdPost,HttpStatus.CREATED);
		
	}
}
