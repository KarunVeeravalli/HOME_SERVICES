package com.homeservices.request.dto;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequest {
	
	@Nonnull
	private String username;
	
	@NotNull
	private String email;
	
	@NotNull
	private String password;
	
	@NotNull
	private Long mobilenumber;
	
	
	
}
