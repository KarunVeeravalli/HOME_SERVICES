package com.homeservices.dto.request;

import com.homeservices.util.CRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailDto extends CRequest{
	
	private String toEmail;
	
	private String body;
	
	private String subject;
	
}
