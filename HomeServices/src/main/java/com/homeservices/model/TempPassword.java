package com.homeservices.model;

import com.homeservices.util.CommonClass;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TempPassword extends CommonClass{
	
	private String email;
	
	private String password;

}
