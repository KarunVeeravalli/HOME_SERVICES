package com.homeservices.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homeservices.enums.Status;
import com.homeservices.exception.UserLoginProfileException;
import com.homeservices.exception.UserProfileException;
import com.homeservices.model.UserProfile;
import com.homeservices.repo.UserProfileRepo;
import com.homeservices.service.UserProfileService;
import com.homeservices.util.RepoHelper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class UserProfileServiceImpl implements UserProfileService{
	
	@Autowired
	private UserProfileRepo repo;
	
	@Autowired
	private RepoHelper helper;
	
	public static final Logger logger = LogManager.getLogger(UserProfileServiceImpl.class);

	@Override
	public Status addUser(UserProfile user, HttpServletRequest request, HttpServletResponse response)
			throws UserProfileException, UserLoginProfileException {
		logger.info("<------ UserProfileServiceImpl : addUser (BEGIN) ------>");
		repo.save(user);
		logger.info("<------ UserProfileServiceImpl : addUser (END) ------>");
		return Status.SUCCESS;
	}

	@Override
	public Status updateUser(UserProfile user, HttpServletRequest request, HttpServletResponse response)
			throws UserProfileException {
		logger.info("<------ UserProfileServiceImpl : updateUser (BEGIN) ------>");
		UserProfile user1 = repo.findByEmail(user.getEmail());
		BeanUtils.copyProperties(user, user1, helper.getNullPropertyNames(user));
		repo.save(user1);
		logger.info("<------ UserProfileServiceImpl : updateUser (END) ------>");
		return Status.SUCCESS;
	}

	@Override
	public Status deleteUserByEmail(String email, HttpServletRequest request, HttpServletResponse response)
			throws UserProfileException {
		logger.info("<------ UserProfileServiceImpl : deleteUserByEmail (BEGIN) ------>");
		repo.deleteByEmail(email);
		logger.info("<------ UserProfileServiceImpl : deleteUserByEmail (END) ------>");
		return Status.SUCCESS;
	}

	@Override
	public UserProfile getUserByEmail(String email, HttpServletRequest request, HttpServletResponse response)
			throws UserProfileException {
		logger.info("<------ UserProfileServiceImpl : getUserByEmail ------>");
		return repo.findByEmail(email);
	}


}
