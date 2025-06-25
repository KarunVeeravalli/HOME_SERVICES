package com.homeservices.dto.request;

import java.util.ArrayList;
import java.util.List;

import com.homeservices.dto.common.CRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TechnicianProfileDto extends CRequest{

	private Boolean isAvailable;
	private Double experience;
	private List<Long> expertise = new ArrayList<>();
	
	private String username;
	private String email;
	private Long mobileNumber;

}
