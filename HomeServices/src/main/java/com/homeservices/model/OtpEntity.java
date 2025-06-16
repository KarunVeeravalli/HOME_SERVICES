package com.homeservices.model;

import com.homeservices.util.CommonClass;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class OtpEntity extends CommonClass{
	private String email;
	
	private String otp;
	
	private String description;
	
}
