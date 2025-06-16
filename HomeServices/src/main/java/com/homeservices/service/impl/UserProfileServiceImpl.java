package com.homeservices.service.impl;

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

	@Override
	public Status addUser(UserProfile user, HttpServletRequest request, HttpServletResponse response)
			throws UserProfileException, UserLoginProfileException {
		repo.save(user);
		return Status.SUCCESS;
	}

	@Override
	public Status updateUser(UserProfile user, HttpServletRequest request, HttpServletResponse response)
			throws UserProfileException {
		UserProfile user1 = repo.findByEmail(user.getEmail());
		BeanUtils.copyProperties(user, user1, helper.getNullPropertyNames(user));
		repo.save(user1);
		return Status.SUCCESS;
	}

	@Override
	public Status deleteUserByEmail(String email, HttpServletRequest request, HttpServletResponse response)
			throws UserProfileException {
		repo.deleteByEmail(email);
		return Status.SUCCESS;
	}

	@Override
	public UserProfile getUserByEmail(String email, HttpServletRequest request, HttpServletResponse response)
			throws UserProfileException {
		return repo.findByEmail(email);
	}


}
