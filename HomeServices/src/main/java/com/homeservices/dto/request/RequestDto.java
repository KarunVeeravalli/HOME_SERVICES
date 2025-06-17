package com.homeservices.dto.request;

import com.homeservices.util.CRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestDto extends CRequest{
	
	private String email;
	
	private String description;
}
