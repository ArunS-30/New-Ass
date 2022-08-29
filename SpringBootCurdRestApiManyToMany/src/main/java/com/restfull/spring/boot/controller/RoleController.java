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

import com.restfull.spring.boot.entity.Role;
import com.restfull.spring.boot.model.RoleModel;
import com.restfull.spring.boot.repository.RoleRepository;
import com.restfull.spring.boot.service.RoleService;

@RestController
public class RoleController {

	private RoleService roleService;

	private RoleRepository roleRepository;

	public RoleController(RoleService roleService, RoleRepository roleRepository) {
		super();
		this.roleService = roleService;
		this.roleRepository = roleRepository;
	}

	@PostMapping("/role/create")
	public ResponseEntity<Object> createRole(@RequestBody Role role) {
		return roleService.createRole(role);
	}

	@DeleteMapping("/role/delete/{id}")
	public ResponseEntity<Object> deleteRole(@PathVariable Long id) {
		return roleService.deleteRole(id);
	}

	@GetMapping("/role/allDetails/{id}")
	public Role getRole(@PathVariable Long id) {
		if (roleRepository.findById(id).isPresent())
			return roleRepository.findById(id).get();
		else
			return null;
	}

	@GetMapping("/role/allDetails")
	public List<Role> getAllRoles() {
		return roleRepository.findAll();
	}

	// @GetMapping("/role/allDetails")
	// public List<RoleModel> getAllRolls() {
	// return roleService.getAllRolls();
	// }
	//
	// @GetMapping("/role/details/{id}")
	// public RoleModel getRole(@PathVariable Long id) {
	// return roleService.getRole(id);
	// }

	@PutMapping("/role/update/{id}")
	public ResponseEntity<Object> updateRole(@PathVariable Long id, @RequestBody Role role) {
		return roleService.updateRole(id, role);
	}

}
