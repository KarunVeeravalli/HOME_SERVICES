package com.homeservices.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PasswordDto{
	
	@NotNull
	private String email;
	
//	@NotNull
	private String oldPassword;
	
	@NotNull
	private String password;
}
