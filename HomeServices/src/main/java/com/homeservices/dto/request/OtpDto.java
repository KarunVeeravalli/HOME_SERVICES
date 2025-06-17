package com.homeservices.dto.request;

import com.homeservices.dto.common.CRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OtpDto extends CRequest{
	
	@NonNull
	private String email;
	
	@NonNull
	private String otp;
}
