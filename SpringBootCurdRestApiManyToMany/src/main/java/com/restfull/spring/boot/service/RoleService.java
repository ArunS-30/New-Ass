package com.restfull.spring.boot.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.restfull.spring.boot.entity.Role;
import com.restfull.spring.boot.entity.User;
import com.restfull.spring.boot.model.RoleModel;
import com.restfull.spring.boot.model.UserModel;
import com.restfull.spring.boot.repository.RoleRepository;
import com.restfull.spring.boot.repository.UserRepository;

@Service
public class RoleService {

	private RoleRepository roleRepository;
	private UserRepository userRepository;

	public RoleService(RoleRepository roleRepository, UserRepository userRepository) {
		super();
		this.roleRepository = roleRepository;
		this.userRepository = userRepository;
	}

	/**
	 * Create a new role
	 */
	@Transactional
	public ResponseEntity<Object> createRole(Role role) {
		Role newRole = new Role();
		newRole.setName(role.getName());
		newRole.setDescription(role.getDescription());
		List<Role> roleList = new ArrayList<>();
		roleList.add(newRole);
		for (int i = 0; i < role.getUser().size(); i++) {
			if (!userRepository.findByEmail(role.getUser().get(i).getEmail()).isPresent()) {
				User newUser = role.getUser().get(i);
				newUser.setRoles(roleList);
				User savedUser = userRepository.save(newUser);
				if (!userRepository.findById(savedUser.getId()).isPresent())
					return ResponseEntity.unprocessableEntity().body("Role Creation Failed");
			} else
				return ResponseEntity.unprocessableEntity().body("User with email id is already Present");
		}
		return ResponseEntity.ok("Successfully Created Role");
	}

	/**
	 * Delete a specified role given the id
	 */
	public ResponseEntity<Object> deleteRole(Long id) {
		if (roleRepository.findById(id).isPresent()) {
			if (roleRepository.getOne(id).getUser().size() == 0) {
				roleRepository.deleteById(id);

				if (roleRepository.findById(id).isPresent()) {
					return ResponseEntity.unprocessableEntity().body("Failed to delete the Specified Record");
				} else
					return ResponseEntity.ok().body("Successfully deleated specified Record");
			} else
				return ResponseEntity.unprocessableEntity()
						.body("Failed to delete the users associated with this role");
		} else
			return ResponseEntity.unprocessableEntity().body("No Record Found");

	}

	/** Update a Role */
	public ResponseEntity<Object> updateRole(Long id, Role role) {

		if (roleRepository.findById(id).isPresent()) {
			Role newRole = roleRepository.findById(id).get();
			newRole.setName(role.getName());
			newRole.setDescription(role.getDescription());
			Role savedRole = roleRepository.save(newRole);
			if (roleRepository.findById(savedRole.getId()).isPresent())
				return ResponseEntity.accepted().body("Role saved successfully");
			else
				return ResponseEntity.badRequest().body("Failed to update Role");

		} else
			return ResponseEntity.unprocessableEntity().body("Specified Role not found");
	}

	/*
	 * public List<RoleModel> getAllRolls() { List<Role> roleList =
	 * roleRepository.findAll(); if (roleList.size() > 0) { List<RoleModel>
	 * roleModels = new ArrayList<>(); for (Role role : roleList) { RoleModel model
	 * = new RoleModel(); model.setName(role.getName());
	 * model.setDescription(role.getDescription());
	 * model.setUser(getUserList(role)); roleModels.add(model); } return roleModels;
	 * } else return new ArrayList<RoleModel>(); }
	 * 
	 * public RoleModel getRole(Long id) { if
	 * (roleRepository.findById(id).isPresent()) { Role role =
	 * roleRepository.findById(id).get(); RoleModel roleModel = new RoleModel();
	 * roleModel.setName(role.getName());
	 * roleModel.setDescription(role.getDescription());
	 * roleModel.setUser(getUserList(role)); return roleModel; } else return null;
	 * 
	 * }
	 * 
	 * private List<UserModel> getUserList(Role role) { List<UserModel> userList =
	 * new ArrayList<>(); for (int i = 0; i < role.getUser().size(); i++) {
	 * UserModel userModel = new UserModel();
	 * userModel.setFirstName(role.getUser().get(i).getFirstName());
	 * userModel.setLastName(role.getUser().get(i).getLastName());
	 * userModel.setMoblile(role.getUser().get(i).getMobile());
	 * userModel.setEmail(role.getUser().get(i).getEmail());
	 * userList.add(userModel); } return userList;
	 * 
	 * }
	 */

}
