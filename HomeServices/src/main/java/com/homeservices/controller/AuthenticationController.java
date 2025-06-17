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
		try {
			String res = service.register(helper.string2Object(signupRequest, SignupRequest.class), request, response);
			gRes.setData(res);
			gRes.setResponseCode(200);
			return ResponseEntity.ok(gRes);
		} catch (Exception e) {
			gRes.getExceptions().add(e);
			gRes.setResponseCode(400);
			return new ResponseEntity<>(gRes,HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@PostMapping("/verifyOtpForRegister")
	public ResponseEntity<GeneralResponse>  verifyOtpForRegister(@RequestBody String dto, HttpServletRequest request, HttpServletResponse response)
			throws UserLoginProfileException, UnAuthUserException, UserProfileException, OtpEntityException {
		GeneralResponse gRes = new GeneralResponse();
		try {
			Status res = service.verifyOtpForRegister(helper.string2Object(dto, OtpDto.class), request, response);
			gRes.setData(res);
			gRes.setResponseCode(200);
			return ResponseEntity.ok(gRes);
		} catch (Exception e) {
			gRes.getExceptions().add(e);
			gRes.setResponseCode(400);
			return new ResponseEntity<>(gRes,HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/changePassword")
	public ResponseEntity<GeneralResponse> changePassword(@RequestBody String dto, HttpServletRequest request, HttpServletResponse response)
			throws UserLoginProfileException, UserProfileException, OtpEntityException, EmailException {
		GeneralResponse gRes = new GeneralResponse();
		try {
			String res = service.changePassword(helper.string2Object(dto, PasswordDto.class), request, response);
			gRes.setData(res);
			gRes.setResponseCode(200);
			return ResponseEntity.ok(gRes);
		} catch (Exception e) {
			gRes.getExceptions().add(e);
			gRes.setResponseCode(400);
			return new ResponseEntity<>(gRes,HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/signin")
	public ResponseEntity<GeneralResponse> login(@RequestBody String dto, HttpServletRequest request, HttpServletResponse response)
			throws UserLoginProfileException, UserProfileException, EmailException {
		GeneralResponse gRes = new GeneralResponse();
		try {
			String res = service.login(helper.string2Object(dto, LoginRequest.class), request, response);
			gRes.setData(res);
			gRes.setResponseCode(200);
			return ResponseEntity.ok(gRes);
		} catch (Exception e) {
			gRes.getExceptions().add(e);
			gRes.setResponseCode(400);
			return new ResponseEntity<>(gRes,HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/getAllUserNames")
	public ResponseEntity<GeneralResponse> getAllUserNames(HttpServletRequest request, HttpServletResponse response)
			throws UserLoginProfileException {
		GeneralResponse gRes = new GeneralResponse();
		try {
			List<String> res = service.getAllUserNames( request, response);
			gRes.setData(res);
			gRes.setResponseCode(200);
			return ResponseEntity.ok(gRes);
		} catch (Exception e) {
			gRes.getExceptions().add(e);
			gRes.setResponseCode(400);
			return new ResponseEntity<>(gRes,HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/verifyOtpForPasswordUpdate")
	public ResponseEntity<GeneralResponse>  verifyOtpForPasswordUpdate(@RequestBody String dto, HttpServletRequest request, HttpServletResponse response)
			throws UserLoginProfileException {
		GeneralResponse gRes = new GeneralResponse();
		try {
			Status res = service.verifyOtpForPasswordUpdate(helper.string2Object(dto, OtpDto.class), request, response);
			gRes.setData(res);
			gRes.setResponseCode(200);
			return ResponseEntity.ok(gRes);
		} catch (Exception e) {
			gRes.getExceptions().add(e);
			gRes.setResponseCode(400);
			return new ResponseEntity<>(gRes,HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/logout")
	public ResponseEntity<GeneralResponse>  logout(HttpServletRequest request, HttpServletResponse response)
			throws UserLoginProfileException, UserProfileException {
		GeneralResponse gRes = new GeneralResponse();
		try {
			Status res = service.logout( request, response);
			gRes.setData(res);
			gRes.setResponseCode(200);
			return ResponseEntity.ok(gRes);
		} catch (Exception e) {
			gRes.getExceptions().add(e);
			gRes.setResponseCode(400);
			return new ResponseEntity<>(gRes,HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/deleteUserByEmail")
	public ResponseEntity<GeneralResponse>  deleteUserByEmail(@RequestBody String dto, HttpServletRequest request, HttpServletResponse response)
			throws UserLoginProfileException, UserProfileException {
		GeneralResponse gRes = new GeneralResponse();
		try {
			Status res = service.deleteUserByEmail(helper.string2Object(dto, RequestDto.class), request, response);
			gRes.setData(res);
			gRes.setResponseCode(200);
			return ResponseEntity.ok(gRes);
		} catch (Exception e) {
			gRes.getExceptions().add(e);
			gRes.setResponseCode(400);
			return new ResponseEntity<>(gRes,HttpStatus.BAD_REQUEST);
		}
	}
	
	@Value("${homeservices.encrypted.secret}")
	private String secret;
	
	@PostMapping("/encrypt")
	public ResponseEntity<GeneralResponse>  encrypt(@RequestBody Object req, HttpServletRequest request, HttpServletResponse response)
			throws UserLoginProfileException, UserProfileException {
		GeneralResponse gRes = new GeneralResponse();
		try {
			String res = helper.object2String(req);
			gRes.setData(res);
			gRes.setResponseCode(200);
			return ResponseEntity.ok(gRes);
		} catch (Exception e) {
			gRes.getExceptions().add(e);
			gRes.setResponseCode(400);
			return new ResponseEntity<>(gRes,HttpStatus.BAD_REQUEST);
		}
	}
	

}
