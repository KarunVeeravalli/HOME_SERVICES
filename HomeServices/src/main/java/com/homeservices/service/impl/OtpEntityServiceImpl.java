package com.homeservices.service.impl;

import java.time.LocalDateTime;

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

	@Override
	public void saveOtp(OtpEntity otp, HttpServletRequest request, HttpServletResponse response)
			throws UserProfileException, OtpEntityException {
		try {
			repo.save(otp);
		} catch (Exception e) {

		}
		
	}

	@Override
	public OtpEntity getLastOtp(String email, HttpServletRequest request, HttpServletResponse response)
			throws UserProfileException, OtpEntityException {
		OtpEntity otp = repo.getLastOtpByEmail(email);
		if(otp==null) {
			throw new OtpEntityException("There is no otp found with the email----> "+email);
		}
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
