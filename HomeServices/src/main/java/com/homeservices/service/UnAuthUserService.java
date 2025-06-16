package com.homeservices.service;

import com.homeservices.enums.Status;
import com.homeservices.exception.UnAuthUserException;
import com.homeservices.exception.UserProfileException;
import com.homeservices.model.UnAuthUser;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface UnAuthUserService {
	
	public UnAuthUser saveUser(UnAuthUser user, HttpServletRequest request, HttpServletResponse response) throws UnAuthUserException, UserProfileException;
	
	public Status deleteUser(String email, HttpServletRequest request, HttpServletResponse response) throws UnAuthUserException, UserProfileException;
	
	public UnAuthUser getUser(String email, HttpServletRequest request, HttpServletResponse response) throws UnAuthUserException;
	
	public UnAuthUser updateUser(UnAuthUser user, HttpServletRequest request, HttpServletResponse response) throws UnAuthUserException;

}
