package com.homeservices.request.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OtpDto {
	
	@NonNull
	private String email;
	
	@NonNull
	private String otp;
}
