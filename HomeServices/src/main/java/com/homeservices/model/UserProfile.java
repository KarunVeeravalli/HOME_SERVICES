package com.homeservices.model;

import java.util.HashSet;
import java.util.Set;

import com.homeservices.util.CommonClass;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfile extends CommonClass{
		
	private String email;
	
	private String username;
	
//	private String password;
	
	private Long mobileNumber;
	
	@Lob
	private String imageInBase64;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "USER_PROFILE_ROLES", joinColumns = @JoinColumn(name="user_id") ,inverseJoinColumns = @JoinColumn(name="role_id"))
	private Set<Role> roles = new HashSet<>();
}
