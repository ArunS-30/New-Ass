package com.restfull.spring.boot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.restfull.spring.boot.entity.User;
import com.restfull.spring.boot.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("/user")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		return new ResponseEntity<User>(userService.createUser(user), HttpStatus.CREATED);
	}

	@PutMapping("/user")
	public ResponseEntity<User> updateUser(@RequestBody User user) {
		return new ResponseEntity<User>(userService.updateUser(user), HttpStatus.CREATED);
	}

	@DeleteMapping("/User/{Id}")
	public ResponseEntity<String> deleteUser(@PathVariable(value = "Id") Long Id) {
		userService.deleteUser(Id);
		return new ResponseEntity<>("User with UserId : " + Id + " deleted successfully", HttpStatus.OK);
	}

	@GetMapping("/user/{Id}")
	public ResponseEntity<Object> getUser(@PathVariable(value = "Id") Long Id) {
		return new ResponseEntity<Object>(userService.getUser(Id), HttpStatus.OK);
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<User>> getAllUsers() {
		return new ResponseEntity<List<User>>(userService.getAllUsers(), HttpStatus.OK);
	}
}
