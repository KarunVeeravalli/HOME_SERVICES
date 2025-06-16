package com.homeservices.service;

import com.homeservices.enums.Status;
import com.homeservices.exception.EmailException;
import com.homeservices.exception.UserProfileException;
import com.homeservices.request.dto.EmailDto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface EmailService {
	
	public Status sendOtp(EmailDto email, HttpServletRequest request, HttpServletResponse response) throws UserProfileException, EmailException;
	
	public Status sendEmail(EmailDto email, HttpServletRequest request, HttpServletResponse response)throws UserProfileException, EmailException;

}
