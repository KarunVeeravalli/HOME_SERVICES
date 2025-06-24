package com.homeservices.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.homeservices.dto.request.AddressDto;
import com.homeservices.enums.AddressType;
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
	public AddressType addressType;
	
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="USERPROFILE_ID")
	public UserProfile userProfile;
	
	 public static Address build(AddressDto dto) {
	        Address address = new Address();
	        address.setState(dto.state);
	        address.setCity(dto.city);
	        address.setVillage(dto.village);
	        address.setDoorNo(dto.doorNo);
	        address.setPincode(dto.pincode);
	        address.setStreet(dto.street);
	        address.setDescription(dto.description);
	        address.setMobileNumber(dto.mobileNumber);
	        address.setAddressType(dto.addressType);
	        address.setId(dto.getId());
	        return address;
	    }
	
	
}