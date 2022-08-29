package com.restfull.spring.boot.model;

import java.util.List;

public class RoleModel {

	private String name;
	private String description;
	// private List<UserModel> user;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	// public List<UserModel> getUser() { return user; }
	//
	// public void setUser(List<UserModel> user) { this.user = user; }

}
