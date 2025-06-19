package com.homeservices.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.homeservices.exception.AddressException;
import com.homeservices.exception.UserProfileException;
import com.homeservices.model.Address;
import com.homeservices.service.AddressService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class AddressServiceImpl implements AddressService{

	@Override
	public Address addAddress(Address address, HttpServletRequest request, HttpServletResponse response)
			throws AddressException, UserProfileException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Address updateAddress(Address address, HttpServletRequest request, HttpServletResponse response)
			throws AddressException, UserProfileException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Address getAddress(Long id, HttpServletRequest request, HttpServletResponse response)
			throws AddressException, UserProfileException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Address> getAllAddresses(HttpServletRequest request, HttpServletResponse response)
			throws AddressException, UserProfileException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteAddress(Long id, HttpServletResponse response, HttpServletRequest request)
			throws AddressException, UserProfileException {
		// TODO Auto-generated method stub
		return null;
	}

}
