package com.restfull.spring.boot.model;

import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserModel {

	private String firstName;
	private String lastName;
	private String moblile;
	private String email;
	private List<RoleModel> roles;

	
	  public String getFirstName() { return firstName; }
	  
	  public void setFirstName(String firstName) { this.firstName = firstName; }
	  
	  public String getLastName() { return lastName; }
	  
	  public void setLastName(String lastName) { this.lastName = lastName; }
	  
	  public String getMoblile() { return moblile; }
	  
	  public void setMoblile(String moblile) { this.moblile = moblile; }
	  
	  public String getEmail() { return email; }
	  
	  public void setEmail(String email) { this.email = email; }
	  
	  public List<RoleModel> getRole() { return roles; }
	  
	  public void setRole(List<RoleModel> roles) { this.roles = roles; }
	 
}
