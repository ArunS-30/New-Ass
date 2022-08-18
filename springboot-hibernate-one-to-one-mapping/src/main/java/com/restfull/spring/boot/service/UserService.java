package com.restfull.spring.boot.service;

import java.util.List;

import com.restfull.spring.boot.entity.User;

public interface UserService {

	public User createUser(User user);

	public User updateUser(User user);

	public void deleteUser(Long IdL);

	public List<User> getAllUsers();

	public Object getUser(Long idL);
}
