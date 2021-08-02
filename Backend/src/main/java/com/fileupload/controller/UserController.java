package com.fileupload.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fileupload.model.User;
import com.fileupload.repository.UserRepository;

@CrossOrigin
@RestController
public class UserController {

	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/users")
	public ResponseEntity<Object> getAllUsers(){
		return new ResponseEntity<> (userRepository.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/users/{id}")
	public ResponseEntity<Object> getUserById(@PathVariable("id") Long id){
		Optional<User> userData = userRepository.findById(id);
		if (userData.isPresent()) {
			return new ResponseEntity<> (userData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<> (null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User requestUser){
		try {
			User postUser = new User();
			postUser.setUserId(requestUser.getUserId());
			postUser.setPassword(requestUser.getPassword());
			postUser.setPhoneNumber(requestUser.getPhoneNumber());
			postUser.setEmail(requestUser.getEmail());
			postUser.setUsername(requestUser.getUsername());
			postUser.setAddress(requestUser.getAddress());
			return new ResponseEntity<>(userRepository.save(postUser), HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println("=============================== Errors: " + e);
			return ResponseEntity.unprocessableEntity().body("Failed to create new user: " + e.getCause().getCause().getMessage());
		}
	}
	
	@PutMapping("/users/{id}")
	public ResponseEntity<Object> updateUserById(@PathVariable("id") Long id,
			@Valid @RequestBody User requestUser){
		Optional<User> userData = userRepository.findById(id);
		if (userData.isPresent()) {
			User updateUser = userData.get();
			updateUser.setUserId(requestUser.getUserId());
			updateUser.setPassword(requestUser.getPassword());
			updateUser.setPhoneNumber(requestUser.getPhoneNumber());
			updateUser.setEmail(requestUser.getEmail());
			updateUser.setUsername(requestUser.getUsername());
			updateUser.setAddress(requestUser.getAddress());
			try {
				return new ResponseEntity<> (userRepository.save(updateUser), HttpStatus.OK);
			} catch (Exception e) {
				System.out.println("=============================== Errors: " + e.getCause().getCause().getMessage());
				return ResponseEntity.unprocessableEntity().body("Failed to create new user: " + e.getCause().getCause().getMessage());
			}
		} else {
			return ResponseEntity.badRequest().body("Failed to update speicfied user by id " + id);
		}
	}
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<Object> deleteUserById(@PathVariable("id") Long id){
		try {
			userRepository.deleteById(id);
			return new ResponseEntity<> (HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<> (null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
