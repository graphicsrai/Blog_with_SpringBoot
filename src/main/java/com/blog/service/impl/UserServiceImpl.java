package com.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.dto.UserDto;
import com.blog.entity.User;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.repository.UserRepo;
import com.blog.service.UserService;
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userrepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		User user=this.dtoToUser(userDto);
		User savedUser=this.userrepo.save(user);
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user=this.userrepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
		
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		
		User updatedUser=this.userrepo.save(user);
		UserDto userDto1=this.userToDto(updatedUser);
		return userDto1;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user=this.userrepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		
		List<User> users=this.userrepo.findAll();
		List<UserDto> userDtos= users.stream().map(user-> this.userToDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = this.userrepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
		this.userrepo.delete(user);
		
	}

	private User dtoToUser(UserDto userDto)
	{
		User user= this.modelMapper.map(userDto, User.class);
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setPassword(userDto.getPassword());
//		user.setAbout(userDto.getAbout());
		return user;
	}
	private UserDto userToDto(User user) 
	{
		UserDto userDto=this.modelMapper.map(user, UserDto.class);
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setPassword(user.getPassword());
//		userDto.setAbout(user.getAbout());
		return userDto;
	}
	
}
