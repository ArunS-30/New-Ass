package com.restfull.spring.boot.controller;

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

import com.restfull.spring.boot.entity.User;
import com.restfull.spring.boot.model.UserModel;
import com.restfull.spring.boot.repository.UserRepository;
import com.restfull.spring.boot.service.UserService;

@RestController
public class UserController {

	private UserService userService;

	private UserRepository userRepository;

	public UserController(UserService userService, UserRepository userRepository) {
		super();
		this.userService = userService;
		this.userRepository = userRepository;
	}

	@PostMapping("/user/create")
	public ResponseEntity<Object> createUser(@RequestBody User user) {
		return userService.createUser(user);
	}

	@GetMapping("/user/details/{id}")
	public UserModel getUser(@PathVariable Long id) {
		return userService.getUser(id);
	}

	@GetMapping("/user/allDetails")
	public List<UserModel> getAllUsers() {
		return userService.getAllUsers();
	}

	@PutMapping("/user/update/{id}")
	public ResponseEntity<Object> updateUser(@PathVariable Long id, @RequestBody User user) {
		return userService.updateUser(user, id);
	}

	@DeleteMapping("user/delete/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable Long id) {
		return userService.deleteUser(id);
	}

}
