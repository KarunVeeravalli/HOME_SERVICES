package com.homeservices.dto.request;

import com.homeservices.dto.common.CRequest;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PasswordDto extends CRequest{
	
	@NotNull
	private String email;
	
//	@NotNull
	private String oldPassword;
	
	@NotNull
	private String password;
}
