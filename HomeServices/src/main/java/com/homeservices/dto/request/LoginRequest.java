package com.homeservices.dto.request;

import com.homeservices.dto.common.CRequest;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest extends CRequest{
	
	@NotBlank
	private String email;

	@NotBlank
	private String password;

}
