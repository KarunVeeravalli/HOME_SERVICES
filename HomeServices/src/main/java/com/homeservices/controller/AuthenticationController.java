package com.homeservices.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.homeservices.dto.common.CRequest;
import com.homeservices.dto.request.LoginRequest;
import com.homeservices.dto.request.OtpDto;
import com.homeservices.dto.request.PasswordDto;
import com.homeservices.dto.request.RequestDto;
import com.homeservices.dto.request.SignupRequest;
import com.homeservices.dto.response.GeneralResponse;
import com.homeservices.enums.Status;
import com.homeservices.exception.EmailException;
import com.homeservices.exception.OtpEntityException;
import com.homeservices.exception.UnAuthUserException;
import com.homeservices.exception.UserLoginProfileException;
import com.homeservices.exception.UserProfileException;
import com.homeservices.service.UserLoginProfileService;
import com.homeservices.util.RepoHelper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

	@Autowired
	private UserLoginProfileService service;
	
	@Autowired
	private RepoHelper helper;
	
	private static final Logger logger = LogManager.getLogger(AuthenticationController.class);
	
	@PostMapping("/signup")
	public ResponseEntity<GeneralResponse> register(@RequestBody String signupRequest, HttpServletRequest request, HttpServletResponse response)
			throws UserLoginProfileException, UnAuthUserException, UserProfileException, EmailException {
		GeneralResponse gRes = new GeneralResponse();
		logger.info("<------ AuthenticationController : register (BEGIN) ------>");
		try {
			SignupRequest req = helper.string2Object(signupRequest, SignupRequest.class);
			logger.info("user's tracking id is : {}", req.getHeader().getTrackingId());
			String res = service.register(req, request, response);
			gRes.setData(res);
			gRes.setResponseCode(200);
			logger.info("<------ AuthenticationController : register (END) ------>");
			return ResponseEntity.ok(gRes);
		} catch (Exception e) {
			gRes.getExceptions().add(e);
			gRes.setResponseCode(400);
			logger.info("<------ AuthenticationController : register (FAILED) ------>");
			return new ResponseEntity<>(gRes,HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@PostMapping("/verifyOtpForRegister")
	public ResponseEntity<GeneralResponse>  verifyOtpForRegister(@RequestBody String dto, HttpServletRequest request, HttpServletResponse response)
			throws UserLoginProfileException, UnAuthUserException, UserProfileException, OtpEntityException {
		GeneralResponse gRes = new GeneralResponse();
		logger.info("<------ AuthenticationController : verifyOtpForRegister (BEGIN) ------>");
		try {
			OtpDto req = helper.string2Object(dto, OtpDto.class);
			logger.info("user's tracking id is : {}", req.getHeader().getTrackingId());
			Status res = service.verifyOtpForRegister(req, request, response);
			gRes.setData(res);
			gRes.setResponseCode(200);
			logger.info("<------ AuthenticationController : verifyOtpForRegister (END) ------>");
			return ResponseEntity.ok(gRes);
		} catch (Exception e) {
			gRes.getExceptions().add(e);
			gRes.setResponseCode(400);
			logger.info("<------ AuthenticationController : verifyOtpForRegister (FAILED) ------>");
			return new ResponseEntity<>(gRes,HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/changePassword")
	public ResponseEntity<GeneralResponse> changePassword(@RequestBody String dto, HttpServletRequest request, HttpServletResponse response)
			throws UserLoginProfileException, UserProfileException, OtpEntityException, EmailException {
		GeneralResponse gRes = new GeneralResponse();
		logger.info("<------ AuthenticationController : changePassword (BEGIN) ------>");
		try {
			PasswordDto req = helper.string2Object(dto, PasswordDto.class);
			logger.info("user's tracking id is : {}", req.getHeader().getTrackingId());
			String res = service.changePassword(req, request, response);
			gRes.setData(res);
			gRes.setResponseCode(200);
			logger.info("<------ AuthenticationController : changePassword (END) ------>");
			return ResponseEntity.ok(gRes);
		} catch (Exception e) {
			gRes.getExceptions().add(e);
			gRes.setResponseCode(400);
			logger.info("<------ AuthenticationController : changePassword (FAILED) ------>");
			return new ResponseEntity<>(gRes,HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/signin")
	public ResponseEntity<GeneralResponse> login(@RequestBody String dto, HttpServletRequest request, HttpServletResponse response)
			throws UserLoginProfileException, UserProfileException, EmailException {
		GeneralResponse gRes = new GeneralResponse();
		logger.info("<------ AuthenticationController : login (BEGIN) ------>");
		try {
			LoginRequest req = helper.string2Object(dto, LoginRequest.class);
			logger.info("user's tracking id is : {}", req.getHeader().getTrackingId());
			String res = service.login(req, request, response);
			gRes.setData(res);
			gRes.setResponseCode(200);
			logger.info("<------ AuthenticationController : login (END) ------>");
			return ResponseEntity.ok(gRes);
		} catch (Exception e) {
			gRes.getExceptions().add(e);
			gRes.setResponseCode(400);
			logger.info("<------ AuthenticationController : login (FAILED) ------>");
			return new ResponseEntity<>(gRes,HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/getAllUserNames")
	public ResponseEntity<GeneralResponse> getAllUserNames(@RequestBody String dto ,HttpServletRequest request, HttpServletResponse response)
			throws UserLoginProfileException {
		GeneralResponse gRes = new GeneralResponse();
		logger.info("<------ AuthenticationController : getAllUserNames (BEGIN) ------>");
		try {
			CRequest req = helper.string2Object(dto, CRequest.class);
			logger.info("user's tracking id is : {}", req.getHeader().getTrackingId());
			List<String> res = service.getAllUserNames( request, response);
			gRes.setData(res);
			gRes.setResponseCode(200);
			logger.info("<------ AuthenticationController : getAllUserNames (END) ------>");
			return ResponseEntity.ok(gRes);
		} catch (Exception e) {
			gRes.getExceptions().add(e);
			gRes.setResponseCode(400);
			logger.info("<------ AuthenticationController : getAllUserNames (FAILED) ------>");
			return new ResponseEntity<>(gRes,HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/verifyOtpForPasswordUpdate")
	public ResponseEntity<GeneralResponse>  verifyOtpForPasswordUpdate(@RequestBody String dto, HttpServletRequest request, HttpServletResponse response)
			throws UserLoginProfileException {
		GeneralResponse gRes = new GeneralResponse();
		logger.info("<------ AuthenticationController : verifyOtpForPasswordUpdate (BEGIN) ------>");
		try {
			OtpDto req = helper.string2Object(dto, OtpDto.class);
			logger.info("user's tracking id is : {}", req.getHeader().getTrackingId());
			Status res = service.verifyOtpForPasswordUpdate(req, request, response);
			gRes.setData(res);
			gRes.setResponseCode(200);
			logger.info("<------ AuthenticationController : verifyOtpForPasswordUpdate (END) ------>");
			return ResponseEntity.ok(gRes);
		} catch (Exception e) {
			gRes.getExceptions().add(e);
			gRes.setResponseCode(400);
			logger.info("<------ AuthenticationController : verifyOtpForPasswordUpdate (FAILED) ------>");
			return new ResponseEntity<>(gRes,HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/logout")
	public ResponseEntity<GeneralResponse>  logout(@RequestBody String dto, HttpServletRequest request, HttpServletResponse response)
			throws UserLoginProfileException, UserProfileException {
		GeneralResponse gRes = new GeneralResponse();
		logger.info("<------ AuthenticationController : logout (BEGIN) ------>");
		try {
			CRequest req = helper.string2Object(dto, CRequest.class);
			logger.info("user's tracking id is : {}", req.getHeader().getTrackingId());
			Status res = service.logout( request, response);
			gRes.setData(res);
			gRes.setResponseCode(200);
			logger.info("<------ AuthenticationController : logout (END) ------>");
			return ResponseEntity.ok(gRes);
		} catch (Exception e) {
			gRes.getExceptions().add(e);
			gRes.setResponseCode(400);
			logger.info("<------ AuthenticationController : logout (FAILED) ------>");
			return new ResponseEntity<>(gRes,HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/deleteUserByEmail")
	public ResponseEntity<GeneralResponse>  deleteUserByEmail(@RequestBody String dto, HttpServletRequest request, HttpServletResponse response)
			throws UserLoginProfileException, UserProfileException {
		GeneralResponse gRes = new GeneralResponse();
		logger.info("<------ AuthenticationController : deleteUserByEmail (BEGIN) ------>");
		try {
			RequestDto req = helper.string2Object(dto, RequestDto.class);
			logger.info("user's tracking id is : {}", req.getHeader().getTrackingId());
			Status res = service.deleteUserByEmail(req, request, response);
			gRes.setData(res);
			gRes.setResponseCode(200);
			logger.info("<------ AuthenticationController : deleteUserByEmail (END) ------>");
			return ResponseEntity.ok(gRes);
		} catch (Exception e) {
			gRes.getExceptions().add(e);
			gRes.setResponseCode(400);
			logger.info("<------ AuthenticationController : deleteUserByEmail (FAILED) ------>");
			return new ResponseEntity<>(gRes,HttpStatus.BAD_REQUEST);
		}
	}
	
	@Value("${homeservices.encrypted.secret}")
	private String secret;
	
	@PostMapping("/encrypt")
	public ResponseEntity<GeneralResponse>  encrypt(@RequestBody Object req, HttpServletRequest request, HttpServletResponse response)
			throws UserLoginProfileException, UserProfileException {
		GeneralResponse gRes = new GeneralResponse();
		logger.info("<------ AuthenticationController : encrypt (BEGIN) ------>");
		try {
			logger.info("user's tracking name is : {}", request.getUserPrincipal().getName());
			String res = helper.object2String(req);
			gRes.setData(res);
			gRes.setResponseCode(200);
			logger.info("<------ AuthenticationController : encrypt (END) ------>");
			return ResponseEntity.ok(gRes);
		} catch (Exception e) {
			gRes.getExceptions().add(e);
			gRes.setResponseCode(400);
			logger.info("<------ AuthenticationController : encrypt (FAILED) ------>");
			return new ResponseEntity<>(gRes,HttpStatus.BAD_REQUEST);
		}
	}
	

}
