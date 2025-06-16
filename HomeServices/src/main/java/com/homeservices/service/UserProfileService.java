package com.homeservices.service;

import com.homeservices.enums.Status;
import com.homeservices.exception.UserLoginProfileException;
import com.homeservices.exception.UserProfileException;
import com.homeservices.model.UserProfile;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface UserProfileService {
	
	public Status addUser(UserProfile user, HttpServletRequest request, HttpServletResponse response) throws UserProfileException, UserLoginProfileException;
	
	public Status updateUser(UserProfile user, HttpServletRequest request, HttpServletResponse response) throws UserProfileException;
	
	public Status deleteUserByEmail(String email, HttpServletRequest request, HttpServletResponse response) throws UserProfileException;
	
	public UserProfile getUserByEmail(String email, HttpServletRequest request, HttpServletResponse response) throws UserProfileException;
}
