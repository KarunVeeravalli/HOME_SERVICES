package com.homeservices.service.impl;

import java.time.LocalDateTime;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homeservices.enums.Status;
import com.homeservices.exception.OtpEntityException;
import com.homeservices.exception.UserProfileException;
import com.homeservices.model.OtpEntity;
import com.homeservices.repo.OtpEntityRepo;
import com.homeservices.service.OtpEntityService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class OtpEntityServiceImpl implements OtpEntityService{
	
	@Autowired
	private OtpEntityRepo repo;
	
	public final static Logger logger = LogManager.getLogger(OtpEntityServiceImpl.class);

	@Override
	public void saveOtp(OtpEntity otp, HttpServletRequest request, HttpServletResponse response)
			throws UserProfileException, OtpEntityException {
		logger.info("<------ OtpEntityServiceImpl : saveOtp (BEGIN) with request => {} ------>",otp);
		try {
			repo.save(otp);
			logger.info("<------ OtpEntityServiceImpl : saveOtp (END) ------>");
		} catch (Exception e) {
			logger.info("<------ OtpEntityServiceImpl : saveOtp (FAILED) ------>");
		}
		
	}

	@Override
	public OtpEntity getLastOtp(String email, HttpServletRequest request, HttpServletResponse response)
			throws UserProfileException, OtpEntityException {
		OtpEntity otp = repo.getLastOtpByEmail(email);
		logger.info("<------ OtpEntityServiceImpl : getLastOtp (BEGIN) with request => {} ------>",email);
		if(otp==null) {
			logger.info("<------ OtpEntityServiceImpl : getLastOtp (FAILED) ------>");
			throw new OtpEntityException("There is no otp found with the email----> "+email);
		}
		logger.info("<------ OtpEntityServiceImpl : getLastOtp (END) ------>");
		return otp;
	}

	@Override
	public Status checkOtp(OtpEntity otp, HttpServletRequest request, HttpServletResponse response)
			throws UserProfileException, OtpEntityException {
		OtpEntity otp1 = repo.getLastOtpByEmail(otp.getEmail());
		if(otp1==null) {
			throw new OtpEntityException("There is no otp found with the email----> "+otp.getEmail());
		}else if(otp1.getCreatedDateTime().plusMinutes(10).isBefore(LocalDateTime.now())) {
			throw new OtpEntityException("Otp Expired please try again");
		}else if(otp.getOtp().equals(otp1.getOtp())) {
			return Status.SUCCESS;
		}
		return Status.FAILED;
	}

}
