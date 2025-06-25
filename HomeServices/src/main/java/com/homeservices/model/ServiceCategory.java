package com.homeservices.model;

import com.homeservices.dto.request.ServiceCategoryDto;
import com.homeservices.util.CommonClass;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ServiceCategory extends CommonClass{
	
	private String name;
	private String description;
	private String iconUrl;
	
	public static ServiceCategory build(ServiceCategoryDto dto) {
		ServiceCategory item = new ServiceCategory();
		item.setDescription(dto.getDescription());
		item.setIconUrl(dto.getIconUrl());
		item.setName(dto.getName());
		return item;
	}
}
