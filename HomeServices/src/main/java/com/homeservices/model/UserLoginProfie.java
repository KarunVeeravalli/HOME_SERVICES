package com.homeservices.model;

import java.util.HashSet;
import java.util.Set;

import com.homeservices.util.CommonClass;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginProfie extends CommonClass {
	
	private String username;
	
	private String email;
	
	private String password;
	
	private Long mobileNumber;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "USER_LOGIN_PROFILE_ROLES", joinColumns = @JoinColumn(name="user_id") ,inverseJoinColumns = @JoinColumn(name="role_id"))
	private Set<Role> roles = new HashSet<>();
	
	public UserLoginProfie(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password= password;
	}
	
} 