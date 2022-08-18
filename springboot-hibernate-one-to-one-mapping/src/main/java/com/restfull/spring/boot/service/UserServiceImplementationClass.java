package com.restfull.spring.boot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restfull.spring.boot.entity.User;
import com.restfull.spring.boot.repository.UserRepository;

@Service
public class UserServiceImplementationClass implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public User createUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public User updateUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public Object getUser(Long Id) {
		Optional<User> optionalEmp = userRepository.findById(Id);
		if (optionalEmp.isPresent()) {
			return optionalEmp.get();
		}else {
		return "gfghf";
	}
		
	}

	@Override
	public void deleteUser(Long Id) {
		userRepository.deleteById(Id);

	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

}
