package com.homeservices.model;

import java.time.LocalDateTime;

import com.homeservices.enums.ServiceStatus;
import com.homeservices.util.CommonClass;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ServiceRequest extends CommonClass{
	
	private String description;
	
	@ManyToOne
	private UserProfile customer;
	
	@ManyToOne
	private UserProfile technician;
	
	@ManyToOne
	private Address serviceAddress;
	
	@ManyToOne
	private ServiceItem serviceItem;
	
	private Double equipmentCharge;
	
	private String customerRemarks;
	
	private ServiceStatus status;
	
	private LocalDateTime requestedAt;
	
	private LocalDateTime completedAt;
	
	
	
}
