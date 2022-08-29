package com.restfull.spring.boot.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.restfull.spring.boot.entity.User;
import com.restfull.spring.boot.model.RoleModel;
import com.restfull.spring.boot.model.UserModel;
import com.restfull.spring.boot.repository.RoleRepository;
import com.restfull.spring.boot.repository.UserRepository;

@Service
public class UserService {
	private UserRepository userRepository;
	private RoleRepository roleRepository;

	public UserService(UserRepository userRepository, RoleRepository roleRepository) {
		super();
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}

	/** Create a new User */

	public ResponseEntity<Object> createUser(User user) {
		User newUser = new User();
		if (userRepository.findByEmail(user.getEmail()).isPresent()) {
			return ResponseEntity.badRequest().body("The Email is already Present, Failed to Create new User");
		} else {
			newUser.setFirstName(user.getFirstName());
			newUser.setLastName(user.getLastName());
			newUser.setMobile(user.getMobile());
			newUser.setEmail(user.getEmail());
			newUser.setRoles(user.getRoles());

			User savedUser = userRepository.save(newUser);
			if (userRepository.findById(savedUser.getId()).isPresent())
				return ResponseEntity.ok("User Created Successfully");
			else
				return ResponseEntity.unprocessableEntity().body("Failed Creating User as Specified");
		}
	}

	/** Update an Existing User */
	@Transactional
	public ResponseEntity<Object> updateUser(User user, Long id) {
		if (userRepository.findById(id).isPresent()) {
			User newUser = userRepository.findById(id).get();
			newUser.setFirstName(user.getFirstName());
			newUser.setLastName(user.getLastName());
			newUser.setMobile(user.getMobile());
			newUser.setEmail(user.getEmail());
			newUser.setRoles(user.getRoles());
			User savedUser = userRepository.save(newUser);
			if (userRepository.findById(savedUser.getId()).isPresent())
				return ResponseEntity.accepted().body("User Updated Successfully");
			else
				return ResponseEntity.unprocessableEntity().body("Failed updating the user specified");
		} else
			return ResponseEntity.unprocessableEntity().body("Cannot find the user specified");
	}

	/** Delete an User */
	public ResponseEntity<Object> deleteUser(Long id) {
		if (userRepository.findById(id).isPresent()) {
			userRepository.deleteById(id);
			if (userRepository.findById(id).isPresent())
				return ResponseEntity.unprocessableEntity().body("Failed to Delete the specified User");
			else
				return ResponseEntity.ok().body("Successfully deleted the specified user");
		} else
			return ResponseEntity.badRequest().body("Cannot find the user specified");

	}

	public List<UserModel> getAllUsers() {
		List<User> userList = userRepository.findAll();
		if (userList.size() > 0) {
			List<UserModel> userModels = new ArrayList<>();
			for (User user : userList) {
				UserModel model = new UserModel();
				model.setFirstName(user.getFirstName());
				model.setLastName(user.getLastName());
				model.setMoblile(user.getMobile());
				model.setEmail(user.getEmail());
				model.setRole(getRoleList(user));
				userModels.add(model);
			}
			return userModels;
		} else
			return new ArrayList<UserModel>();
	}

	public UserModel getUser(Long id) {
		if (userRepository.findById(id).isPresent()) {
			User user = userRepository.findById(id).get();
			UserModel userModel = new UserModel();
			userModel.setFirstName(user.getFirstName());
			userModel.setLastName(user.getLastName());
			userModel.setEmail(user.getEmail());
			userModel.setMoblile(user.getMobile());
			userModel.setRole(getRoleList(user));
			return userModel;
		} else
			return null;

	}

	private List<RoleModel> getRoleList(User user) {
		List<RoleModel> roleList = new ArrayList<>();
		for (int i = 0; i < user.getRoles().size(); i++) {
			RoleModel roleModel = new RoleModel();
			roleModel.setName(user.getRoles().get(i).getName());
			roleModel.setDescription(user.getRoles().get(i).getDescription());
			roleList.add(roleModel);
		}
		return roleList;

	}
}
