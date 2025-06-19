package com.homeservices.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailDto {
	
	private String toEmail;
	
	private String body;
	
	private String subject;
	
}
