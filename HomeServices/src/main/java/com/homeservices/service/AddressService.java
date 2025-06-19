package com.homeservices.service;

import java.util.List;

import com.homeservices.exception.AddressException;
import com.homeservices.exception.UserProfileException;
import com.homeservices.model.Address;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface AddressService {
	
	public Address addAddress(Address address, HttpServletRequest request, HttpServletResponse response) throws AddressException, UserProfileException;
	
	public Address updateAddress(Address address, HttpServletRequest request, HttpServletResponse response) throws AddressException, UserProfileException;
	
	public Address getAddress(Long id,HttpServletRequest request, HttpServletResponse response) throws AddressException, UserProfileException;
	
	public List<Address> getAllAddresses(HttpServletRequest request, HttpServletResponse response) throws AddressException, UserProfileException;
	
	public String deleteAddress(Long id, HttpServletResponse response, HttpServletRequest request) throws AddressException, UserProfileException;
	
}
