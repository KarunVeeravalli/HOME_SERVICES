package com.homeservices.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.homeservices.dto.request.EmailDto;
import com.homeservices.enums.Status;
import com.homeservices.exception.EmailException;
import com.homeservices.exception.OtpEntityException;
import com.homeservices.exception.UserProfileException;
import com.homeservices.model.Email;
import com.homeservices.model.OtpEntity;
import com.homeservices.repo.EmailRepo;
import com.homeservices.service.EmailService;
import com.homeservices.service.OtpEntityService;
import com.homeservices.util.RepoHelper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class EmailServiceImpl implements EmailService {
	
	@Autowired
	private EmailRepo repo;
	
	@Autowired
	private RepoHelper helper;
	
	@Autowired
	private OtpEntityService otpEntityService;
	
	@Autowired
	private JavaMailSender sender;
	
	@Override
	public Status sendOtp(EmailDto email, HttpServletRequest request, HttpServletResponse response)
			throws UserProfileException, EmailException {
		
		String otp = helper.getOtp();
		String body = "Hello email,\n"
				+ "\n"
				+ "Thank you for choosing Our Company.\n"
				+ "\n"
				+ "To complete your registration, please use the One-Time Password (OTP) below:\n"
				+ "\n"
				+ "                 "+otp+"\n"
				+ "\n"
				+ "This OTP is valid for 10 minutes. Please do not share it with anyone.\n"
				+ "\n"
				+ "If you did not request this, please ignore this email.\n"
				+ "\n"
				+ "Best regards,  \n"
				+ "Our Company Team  \n"
				+ "\n"
				+ "-----------------------------  \n"
				+ "Contact us: kkarun701@gmail.com\n"
				+ "Website: [Your Website URL]  \n"
				+ "";
		Email mail = new Email();
		mail.setBody(body);
		mail.setFromEmail(helper.getMail());
		mail.setSubject(email.getSubject());
		mail.setToEmail(email.getToEmail());
		repo.save(mail);
		
		OtpEntity otpEntity = new OtpEntity();
		otpEntity.setDescription(email.getSubject());
		otpEntity.setEmail(email.getToEmail());
		otpEntity.setOtp(otp);
		try {
			otpEntityService.saveOtp(otpEntity, request, response);
		} catch (UserProfileException | OtpEntityException e) {
			e.printStackTrace();
		}
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(helper.getMail());
		message.setText(body);
		message.setSubject(mail.getSubject());
		message.setTo(email.getToEmail());
		sender.send(message);
		return Status.SUCCESS;
	}

	@Override
	public Status sendEmail(EmailDto email, HttpServletRequest request, HttpServletResponse response)
			throws UserProfileException, EmailException {
		Email mail = new Email();
		mail.setBody(email.getBody());
		mail.setFromEmail(helper.getMail());
		mail.setSubject(email.getSubject());
		mail.setToEmail(email.getToEmail());
		repo.save(mail);	
		
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(helper.getMail());
		message.setText(email.getBody());
		message.setSubject(email.getSubject());
		message.setTo(email.getToEmail());
		sender.send(message);		
		return Status.SUCCESS;
	}

}
