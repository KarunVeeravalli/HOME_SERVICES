package com.homeservices.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homeservices.exception.AddressException;
import com.homeservices.exception.UserProfileException;
import com.homeservices.model.Address;
import com.homeservices.model.UserProfile;
import com.homeservices.repo.AddressRepo;
import com.homeservices.repo.UserProfileRepo;
import com.homeservices.service.AddressService;
import com.homeservices.util.RepoHelper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class AddressServiceImpl implements AddressService{
	
	@Autowired
	private AddressRepo repo;
	
	@Autowired
	private RepoHelper helper;
	
	@Autowired
	private UserProfileRepo userProfileRepo;
	
	public static final Logger logger = LogManager.getLogger(AddressServiceImpl.class);

	@Override
	public Address addAddress(Address address, HttpServletRequest request, HttpServletResponse response)
			throws AddressException, UserProfileException {
		logger.info("<------ AddressServiceImpl : addAddress (BEGIN) with request => {} ------>",address);
//		repo.save(address);
		try {
			UserProfile userProfile =  helper.getUserProfile(request);
			address.setUserProfile(userProfile);
			userProfile.getAddresses().add(address);			
			userProfileRepo.save(userProfile);
		} catch (Exception e) {
			logger.info("<------ AddressServiceImpl : addAddress (FAILED) ------>"+e.getMessage());
		}
		logger.info("<------ AddressServiceImpl : addAddress (END) with response => {} ------>",address);
		return address;
	}

	@Override
	public Address updateAddress(Address address, HttpServletRequest request, HttpServletResponse response)
			throws AddressException, UserProfileException {
		Address oldAddress = repo.findById(address.getId()).get();
		logger.info("<------ AddressServiceImpl : updateAddress (BEGIN) with request => {} ------>",address);
		try {
			BeanUtils.copyProperties(address, oldAddress, helper.getNullPropertyNames(address));
			repo.save(oldAddress);
			
		} catch (Exception e) {
			logger.info("<------ AddressServiceImpl : updateAddress (FAILED) ------>"+e.getMessage());
		}
		logger.info("<------ AddressServiceImpl : updateAddress (END) with response => {} ------>",oldAddress);
		return oldAddress ;
	}

	@Override
	public Address getAddress(Long id, HttpServletRequest request, HttpServletResponse response)
			throws AddressException, UserProfileException {
		logger.info("<------ AddressServiceImpl : getAddress (BEGIN) with request => {} ------>",id);
		Address address = repo.findById(id).get();
//		if(address==null) {
//			logger.info("<------ AddressServiceImpl : getAddress (FAILED) ------>");
//		}
		logger.info("<------ AddressServiceImpl : getAddress (END) with response => {} ------>",address);
		return address;
	}

	@Override
	public List<Address> getAllAddresses(HttpServletRequest request, HttpServletResponse response)
			throws AddressException, UserProfileException {
		UserProfile userProfile = helper.getUserProfile(request);
		logger.info("<------ AddressServiceImpl : getAllAddresses (BEGIN) with request => {} ------>",userProfile.getId());
//		if(userProfile.getAddresses().size()==0) {
//			logger.info("<------ AddressServiceImpl : getAllAddresses (FAILED) ------>");
//		}
		logger.info("<------ AddressServiceImpl : getAllAddresses (END) with response => {} ------>",userProfile.getAddresses());
		return userProfile.getAddresses();
	}

	@Override
	public String deleteAddress(Long id, HttpServletResponse response, HttpServletRequest request)
			throws AddressException, UserProfileException {
		logger.info("<------ AddressServiceImpl : deleteAddress (BEGIN) with request => {} ------>",id);
		Address address = repo.findById(id).get();
		UserProfile userProfile = helper.getUserProfile(request);
		if(!address.getUserProfile().getId().equals(userProfile.getId())) {
			logger.info("<------ AddressServiceImpl : deleteAddress (FAILED) ------>");
			throw new UserProfileException("Address Id is mismatching please try again ");
		}
		try {
			repo.deleteById(id);
		} catch (Exception e) {
			logger.info("<------ AddressServiceImpl : deleteAddress (FAILED) ------>"+e.getMessage());
		}
		logger.info("<------ AddressServiceImpl : deleteAddress (END) with response => {} ------>","DELETED successfully");
		return "DELETED successfully";
	}

}
