package com.blog.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blog.dto.PostDto;
import com.blog.dto.PostResponse;
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
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","POst Id", postId));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		Post updatedPost=this.postRepo.save(post);
		return this.modelMapper.map(updatedPost, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","POst Id", postId));
		this.postRepo.delete(post);
		
		
	}

	@Override
	public PostDto getPost(Integer postId) {
		Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","Post Id", postId));
		
		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public PostResponse getPosts(Integer pageNumber,Integer pageSize,String sortBy,String sortDir) {
		
		Sort sort=null;
		if(sortDir.equalsIgnoreCase("asc"))
		{
			sort=Sort.by(sortBy).ascending();
		}
		else
		{
			sort=Sort.by(sortBy).descending(); 
		}
		
		Pageable p = PageRequest.of(pageNumber, pageSize,sort);
		
		Page<Post> pagePost=this.postRepo.findAll(p);
		List<Post> allPost=pagePost.getContent();
		List<PostDto> postDtos=allPost.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		PostResponse postResponse=new PostResponse();
		
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());
		
		return postResponse;
	}

	@Override
	public List<PostDto> getPostByCategory(Integer categoryId) {
		
		Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","category Id", categoryId));
		List <Post> posts=this.postRepo.findByCategory(cat);
		List<PostDto> postList=posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
				
		return postList;
	}

	@Override
	public List<PostDto> getPostByUser(Integer userId) {
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "User Id", userId));
		List<Post> posts=this.postRepo.findByUser(user);
		List<PostDto> postList=posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
				
		return postList;
	}

	@Override
	public List<PostDto> searchPost(String keyword) {
		List<Post> posts=this.postRepo.findByTitleContaining(keyword);
		List<PostDto> postDtos=posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		return postDtos;
	}
	
	
	
}
