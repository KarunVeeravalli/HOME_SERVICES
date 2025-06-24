package com.homeservices.dto.request;

import com.homeservices.dto.common.CRequest;
import com.homeservices.enums.AddressType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto extends CRequest{
//	public Long id;
	public String state;
	public String city;
	public String village;
	public String doorNo;
	public String pincode;
	public String street;
	public String description;
	public String mobileNumber;
	public AddressType addressType;
}
