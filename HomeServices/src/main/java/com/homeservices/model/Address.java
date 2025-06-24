package com.homeservices.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.homeservices.util.CommonClass;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Address extends CommonClass{
	
	public String state;
	public String city;
	public String village;
	public String doorNo;
	public String pincode;
	public String street;
	public String description;
	public String mobileNumber;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="USERPROFILE_ID")
	public UserProfile userProfile;
}
