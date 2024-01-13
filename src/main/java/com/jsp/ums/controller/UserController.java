package com.jsp.ums.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.ums.entity.User;
import com.jsp.ums.requestdto.UserRequest;
import com.jsp.ums.responsedto.UserResponse;
import com.jsp.ums.service.UserService;
import com.jsp.ums.util.ResponseStructure;

import jakarta.validation.Valid;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping(path = "user")
	public ResponseEntity<ResponseStructure<UserResponse>> saveUser(@RequestBody @Valid UserRequest userRequest) {
		 return userService.saveUser(userRequest);
	}
	
	@PutMapping(path = "user/{id}")
	public ResponseEntity<ResponseStructure<UserResponse>> updateUser( @PathVariable int id,@RequestBody User user) {
		 return userService.updateUser(user,id);
	}
	
	@GetMapping(path = "users")
	public ResponseStructure<List<UserResponse>> fetchAllUsers() {
		return userService.fetchAllUsers();
	}
	
	@GetMapping(path = "user/{id}")
	public UserResponse fetchUserById(@PathVariable int id) {
		return userService.fetchUserById(id);
	}
	
	@DeleteMapping(path = "user/{id}")
	public ResponseEntity<ResponseStructure<UserResponse>> deleteUser(@PathVariable int id) {
		return userService.deleteUser(id);
	}
}
