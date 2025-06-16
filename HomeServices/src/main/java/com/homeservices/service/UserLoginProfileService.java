package com.homeservices.service;

import java.util.List;

import com.homeservices.enums.Status;
import com.homeservices.exception.EmailException;
import com.homeservices.exception.OtpEntityException;
import com.homeservices.exception.UnAuthUserException;
import com.homeservices.exception.UserLoginProfileException;
import com.homeservices.exception.UserProfileException;
import com.homeservices.request.dto.LoginRequest;
import com.homeservices.request.dto.OtpDto;
import com.homeservices.request.dto.PasswordDto;
import com.homeservices.request.dto.RequestDto;
import com.homeservices.request.dto.SignupRequest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface UserLoginProfileService {

	
	public String register(SignupRequest signupRequest, HttpServletRequest request, HttpServletResponse response) throws UserLoginProfileException, UnAuthUserException, UserProfileException, EmailException;
	
	public Status verifyOtpForRegister(OtpDto dto, HttpServletRequest request, HttpServletResponse response) throws  UserLoginProfileException, UnAuthUserException, UserProfileException, OtpEntityException;
	
	public String changePassword(PasswordDto dto, HttpServletRequest request, HttpServletResponse response) throws  UserLoginProfileException, UserProfileException, OtpEntityException, EmailException;
	
	public String login(LoginRequest loginRequest,HttpServletRequest request,HttpServletResponse response) throws UserLoginProfileException, UserProfileException, EmailException;
	
	public List<String> getAllUserNames( HttpServletRequest request, HttpServletResponse response) throws UserLoginProfileException;
	
	public Status verifyOtpForPasswordUpdate(OtpDto dto, HttpServletRequest request, HttpServletResponse response) throws  UserLoginProfileException;
	
	public Status logout(HttpServletRequest request,HttpServletResponse response) throws UserLoginProfileException,UserProfileException;
	
	public Status deleteUserByEmail( RequestDto email,HttpServletRequest request,HttpServletResponse response) throws UserLoginProfileException,UserProfileException; 


}
