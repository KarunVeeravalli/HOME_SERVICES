package com.homeservices.service;

import com.homeservices.enums.Status;
import com.homeservices.exception.OtpEntityException;
import com.homeservices.exception.UserProfileException;
import com.homeservices.model.OtpEntity;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface OtpEntityService {
	
	public void saveOtp(OtpEntity otp, HttpServletRequest request, HttpServletResponse response) throws UserProfileException, OtpEntityException;
	
	public OtpEntity getLastOtp(String email, HttpServletRequest request, HttpServletResponse response) throws UserProfileException, OtpEntityException;
	
	public Status checkOtp(OtpEntity otp, HttpServletRequest request, HttpServletResponse response) throws UserProfileException, OtpEntityException;

}
