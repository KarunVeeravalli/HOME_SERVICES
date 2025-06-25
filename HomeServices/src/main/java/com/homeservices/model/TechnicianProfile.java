package com.homeservices.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.homeservices.dto.request.TechnicianProfileDto;
import com.homeservices.util.CommonClass;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TechnicianProfile extends CommonClass{
	
	@OneToOne
	@JsonIgnore
	private UserProfile userProfile;
	
	private Boolean isAvailable;
	private Double experience;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "TECHNICIAN_PROFILE_EXPERTISE", joinColumns = @JoinColumn(name="TECHNICIAN_ID") ,inverseJoinColumns = @JoinColumn(name="SERVICE_CATEGORY_ID"))
	private List<ServiceCategory> expertise = new ArrayList<>();
	
}
