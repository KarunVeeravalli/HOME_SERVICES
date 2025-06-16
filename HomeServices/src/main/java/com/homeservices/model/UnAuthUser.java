package com.homeservices.model;

import com.homeservices.util.CommonClass;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnAuthUser extends CommonClass{
	
	private String username;
	
	private String email;
	
	private String password;
	
	private Long mobileNum;
	
}
