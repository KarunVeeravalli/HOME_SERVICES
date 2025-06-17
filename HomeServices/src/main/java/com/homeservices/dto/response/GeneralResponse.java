package com.homeservices.dto.response;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeneralResponse {
	
	private List<Error> errors = new ArrayList<>();
	private List<Exception> exceptions = new ArrayList<>();
	private Object data;
	private Integer responseCode ;
	
}
