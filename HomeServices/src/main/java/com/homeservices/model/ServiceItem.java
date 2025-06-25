package com.homeservices.model;

import com.homeservices.dto.request.ServiceItemDto;
import com.homeservices.util.CommonClass;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ServiceItem extends CommonClass{
	
	private String name;
	private String description;
	private Boolean isIncludedInSubscription;
	private Double baseCharge;	
	
	@ManyToOne
	private ServiceCategory category;
	
	public static ServiceItem build(ServiceItemDto dto) {
		ServiceItem si = new ServiceItem();
		si.setName(dto.getName());
		si.setDescription(dto.getDescription());
		si.setIsIncludedInSubscription(dto.getIsIncludedInSubscription());
		si.setBaseCharge(dto.getBaseCharge());
		return si;
	}
}
