package com.homeservices.dto.request;

import com.homeservices.dto.common.CRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceItemDto extends CRequest{
	private String name;
	private String description;
	private Boolean isIncludedInSubscription;
	private Double baseCharge;	
	private Long categoryId;
}
