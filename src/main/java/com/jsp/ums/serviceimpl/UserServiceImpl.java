package com.jsp.ums.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.ums.entity.User;
import com.jsp.ums.exception.UserNotFoundException;
import com.jsp.ums.repo.UserRepo;
import com.jsp.ums.requestdto.UserRequest;
import com.jsp.ums.responsedto.UserResponse;
import com.jsp.ums.service.UserService;
import com.jsp.ums.util.ResponseStructure;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	ResponseStructure<UserResponse> responseStructure;
	
	@Autowired
	ResponseStructure<List<UserResponse>> userResponseList;

	public UserResponse fetchUserById(int id) {
		User user = userRepo.findById(id).orElseThrow(() -> new UserNotFoundException("No user found",HttpStatus.NOT_FOUND,"No Users present in the database"));
		return mapToUserResponse(user);
	}

	public ResponseStructure<List<UserResponse>> fetchAllUsers() {
		List<User> list = new ArrayList<User>();
		try {
			list = userRepo.findAll();			
		} catch (Exception e) {
			throw new UserNotFoundException("No user found",HttpStatus.NOT_FOUND,"No Users present in the database");
		}
		List<UserResponse> listResponse = new ArrayList<UserResponse>();
		for(User user:list) {
			listResponse.add(mapToUserResponse(user));
		}
		userResponseList.setData(listResponse);
		userResponseList.setStatus(HttpStatus.FOUND.value());
		userResponseList.setMessage("All Users fetched successfully");
		return userResponseList;
	}

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> deleteUser(int userid) {
		User user1 = userRepo.findById(userid).orElseThrow(() -> new RuntimeException());
		userRepo.delete(user1);
		responseStructure.setStatus(HttpStatus.GONE.value());
		responseStructure.setData(mapToUserResponse(user1));
		responseStructure.setMessage("Data Deleted Successfully");
		return new ResponseEntity<ResponseStructure<UserResponse>>(responseStructure, HttpStatus.GONE);
	}

	public ResponseEntity<ResponseStructure<UserResponse>> saveUser(UserRequest userRequest) {
		User user2 = userRepo.save(mapToUser(userRequest));
		responseStructure.setData(mapToUserResponse(user2));
		responseStructure.setMessage("User successfully inserted");
		responseStructure.setStatus(HttpStatus.CREATED.value());
		return new ResponseEntity<ResponseStructure<UserResponse>>(responseStructure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<UserResponse>> updateUser(User user, int id) {
		User user2 = userRepo.findById(id).map(u -> {
			user.setUserId(id);
			return userRepo.save(user);
		}).orElseThrow(() -> new RuntimeException());
		responseStructure.setData(mapToUserResponse(user2));
		responseStructure.setMessage("User successfully updated");
		responseStructure.setStatus(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<UserResponse>>(responseStructure, HttpStatus.OK);
	}

	private User mapToUser(UserRequest userRequest) {
		return User.builder().userName(userRequest.getUserName()).email(userRequest.getEmail())
				.password(userRequest.getPassword()).build();
	}

	private UserResponse mapToUserResponse(User user) {
		return UserResponse.builder().userId(user.getUserId()).email(user.getEmail()).userName(user.getUserName())
				.build();
	}
}
