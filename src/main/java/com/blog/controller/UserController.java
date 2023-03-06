package com.blog.controller;

import java.util.List;

import javax.validation.Valid;

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
import com.blog.dto.UserDto;
import com.blog.service.UserService;

@RestController
@Validated
@RequestMapping("/api/user")

public class UserController {
	
	@Autowired
	private UserService userservice;
	
	
//	POST -create user
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@RequestBody @Valid UserDto userDto)
	{
		UserDto createUserDto=this.userservice.createUser(userDto);
		return new ResponseEntity<>(createUserDto,HttpStatus.CREATED);
	}
//	PUT -update user
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto>updateUser(@RequestBody UserDto userDto, @PathVariable Integer userId)
	{
		UserDto updatedUser=userservice.updateUser(userDto, userId);
		return ResponseEntity.ok(updatedUser);
	}
//	GET -get user
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUsers()
	{
		return ResponseEntity.ok(this.userservice.getAllUsers());
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getOneUser(@PathVariable Integer userId)
	{
		return ResponseEntity.ok(this.userservice.getUserById(userId));
	}
	
//	DELETE -Delete user
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId)
	{
		userservice.deleteUser(userId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted Succesfully", true),HttpStatus.OK);	
	}
}
