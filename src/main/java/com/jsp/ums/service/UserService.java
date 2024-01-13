package com.jsp.ums.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.jsp.ums.entity.User;
import com.jsp.ums.requestdto.UserRequest;
import com.jsp.ums.responsedto.UserResponse;
import com.jsp.ums.util.ResponseStructure;

public interface UserService {

	public UserResponse fetchUserById(int id);
	
	public ResponseStructure<List<UserResponse>> fetchAllUsers();
	
	public ResponseEntity<ResponseStructure<UserResponse>> deleteUser(int id);
	
	public ResponseEntity<ResponseStructure<UserResponse>> saveUser(UserRequest user);
	
	public ResponseEntity<ResponseStructure<UserResponse>> updateUser(User user, int id);	
	
}
