package com.homeservices.service.impl;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.homeservices.dto.request.EmailDto;
import com.homeservices.dto.request.LoginRequest;
import com.homeservices.dto.request.OtpDto;
import com.homeservices.dto.request.PasswordDto;
import com.homeservices.dto.request.RequestDto;
import com.homeservices.dto.request.SignupRequest;
import com.homeservices.enums.Status;
import com.homeservices.enums.URole;
import com.homeservices.exception.EmailException;
import com.homeservices.exception.OtpEntityException;
import com.homeservices.exception.UnAuthUserException;
import com.homeservices.exception.UserLoginProfileException;
import com.homeservices.exception.UserProfileException;
import com.homeservices.model.OtpEntity;
import com.homeservices.model.Role;
import com.homeservices.model.TempPassword;
import com.homeservices.model.UnAuthUser;
import com.homeservices.model.UserLoginProfie;
import com.homeservices.model.UserProfile;
import com.homeservices.repo.OtpEntityRepo;
import com.homeservices.repo.RoleRepo;
import com.homeservices.repo.TempPasswordRepo;
import com.homeservices.repo.UserLoginProfileRepo;
import com.homeservices.security.util.JwtUtils;
import com.homeservices.security.util.TokenBlackList;
import com.homeservices.service.EmailService;
import com.homeservices.service.OtpEntityService;
import com.homeservices.service.UnAuthUserService;
import com.homeservices.service.UserLoginProfileService;
import com.homeservices.service.UserProfileService;
import com.homeservices.util.RepoHelper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class UserLoginProfileServiceImpl implements UserLoginProfileService{
	
	@Autowired
	private UserLoginProfileRepo repo;
	
	@Autowired
	private UnAuthUserService unAuthUserService;
	
	@Autowired
	private UserProfileService userProfileService;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private OtpEntityService otpEntityService;

	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	private RoleRepo roleRepo;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private RepoHelper helper;
	
	@Autowired
	private TempPasswordRepo tempPasswordRepo;
	
	@Autowired
	private OtpEntityRepo otpEntityRepo;
	
	@Autowired
	private TokenBlackList tokenBlackList;
	
	@Override
	public String register(SignupRequest signupRequest, HttpServletRequest request, HttpServletResponse response)
			throws UserLoginProfileException, UnAuthUserException, UserProfileException, EmailException {
		if(null!=repo.findByEmail(signupRequest.getEmail())) {
			throw new UserLoginProfileException("User Already exists with email---> "+signupRequest.getEmail());
		}
		if(null!=repo.findByUsername(signupRequest.getUsername())) {
			throw new UserLoginProfileException("User Already exists with User name---> "+signupRequest.getUsername());
		}
		if(null!=repo.findByMobileNumber(signupRequest.getMobilenumber())) {
			throw new UserLoginProfileException("User Already exists with Mobile Number---> "+signupRequest.getMobilenumber());
		}
		UnAuthUser user = unAuthUserService.getUser(signupRequest.getEmail(), request, response);
		EmailDto dto = new EmailDto();
		dto.setToEmail(signupRequest.getEmail());
		
		if(user!=null) {
			dto.setSubject("User ALready Added, Please re-try with this OTP");
			emailService.sendOtp(dto, request, response);
			return "User Details Already Added please proceed with otp";
//			throw new UnAuthUserException("User Already registred please complete the registration by otp");
		}
		UnAuthUser unAuthUser = new UnAuthUser();
		unAuthUser.setEmail(signupRequest.getEmail());
		unAuthUser.setMobileNum(signupRequest.getMobilenumber());
		unAuthUser.setPassword(encoder.encode(signupRequest.getPassword()));
		unAuthUser.setUsername(signupRequest.getUsername());
		unAuthUserService.saveUser(unAuthUser, request, response);
		dto.setSubject("One Time Password for your Regsistration");
		emailService.sendOtp(dto, request, response);
		
		return "OTP sent Successfully";
	}

	@Override
	public Status verifyOtpForRegister(OtpDto dto, HttpServletRequest request, HttpServletResponse response)
			throws UserLoginProfileException, UnAuthUserException, UserProfileException, OtpEntityException {
		UnAuthUser user = unAuthUserService.getUser(dto.getEmail(), request, response);
		
		if(user!=null) {
			OtpEntity entity = new OtpEntity();
			entity.setEmail(dto.getEmail());
			entity.setOtp(dto.getOtp());
			try {
				Status stat = otpEntityService.checkOtp(entity, request, response);
				if(stat.equals(Status.FAILED)) {
					throw new OtpEntityException("Invalid OTP , please try again");
				}
				UserLoginProfie profile = new UserLoginProfie();
				profile.setEmail(user.getEmail());
				profile.setMobileNumber(user.getMobileNum());
				profile.setPassword(user.getPassword());
				profile.setUsername(user.getUsername());
				Role role = roleRepo.findByName(URole.ROLE_USER);
				Set<Role> roles = new HashSet<>();
				roles.add(role);
				profile.setRoles(roles);
				repo.save(profile);
				
				UserProfile userProfile = new UserProfile();
				userProfile.setEmail(user.getEmail());
				userProfile.setMobileNumber(user.getMobileNum());
//				userProfile.setPassword(user.getPassword());
				userProfile.setUsername(user.getUsername());
				userProfile.setRoles(roles);
				userProfileService.addUser(userProfile, request, response);
				
				unAuthUserService.deleteUser(dto.getEmail(), request, response);
				
				EmailDto emailDto = new EmailDto();
				emailDto.setToEmail(dto.getEmail());
				emailDto.setBody("Hi [User's Name],\n"
						+ "\n"
						+ "Thanks for registering with [YourAppName]!\n"
						+ "\n"
						+ "Your account has been successfully created. You're now part of a growing community of [your app's purpose – e.g., learners, creators, explorers, etc.].\n"
						+ "\n"
						+ "Here’s what you can do next:\n"
						+ "- Log in and explore your dashboard.\n"
						+ "- Complete your profile to get personalized recommendations.\n"
						+ "- Start using [key feature].\n"
						+ "\n"
						+ "If you have any questions or feedback, we’re just a click away.\n"
						+ "\n"
						+ "Welcome aboard!  \n"
						+ "The [YourAppName] Team\n"
						+ "");
				emailDto.setSubject("Registration Successfull");
				emailService.sendEmail(emailDto, request, response);
				
				return Status.SUCCESS;
			} catch (Exception e) {
				throw new OtpEntityException("Otp Was invalid please try again after sometime");
			}
			
		}
		throw new UnAuthUserException("User doesn't Registered with email ----> "+dto.getEmail());
	}

	@Override
	public String changePassword(PasswordDto dto, HttpServletRequest request, HttpServletResponse response)
			throws UserLoginProfileException, UserProfileException, OtpEntityException, EmailException {
		if(!helper.isUserExistsByEmail(dto.getEmail())) {
			throw new UserLoginProfileException("User Doesn't exists with email ----> "+dto.getEmail());
		}
		UserLoginProfie user = repo.findByEmail(dto.getEmail());
		if(encoder.matches(dto.getOldPassword(), user.getPassword())) {
			TempPassword tempPassword = new TempPassword(dto.getEmail(), encoder.encode(dto.getPassword()));
			tempPasswordRepo.save(tempPassword);
			EmailDto  emailDto = new EmailDto();
			emailDto.setSubject("OTP FOR PASSWORD CHANGE");
			emailService.sendOtp(emailDto, request, response);
			return "OTP SENT SUCCESSFULLY";
		}
		throw new UserLoginProfileException("Password is invalid please try again");
	}

	@Override
	public String login(LoginRequest loginRequest, HttpServletRequest request, HttpServletResponse response)
			throws UserLoginProfileException, UserProfileException, EmailException {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		EmailDto emailDto = new EmailDto();
		emailDto.setToEmail(loginRequest.getEmail());
		String body = "Dear Customer,\n" + "\n" + "\n" + "\n" + "\n" + "\n" + " "
				+ "There's been a new sign-in to your XXXX Application account associated with the email address "
				+ "\n" + loginRequest.getEmail() + ",  on " + LocalDateTime.now() + ".\n" + "\n"
				+ "If this wasn't you, you need to change your E-com Application account password to protect your account."
				+ "\n" + "\n" + "http://localhost:8080/api/auth/changePassword to change password"

				+ "In case you have any queries / clarifications, please call us at our Customer Service number :\n"
				+ "\n" + "8340018900\n" + "8340018900\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n"
				+ " Thank You For Using our E-com Application :) ";
		emailDto.setBody(body);
		emailDto.setSubject("Login Successfull");
		emailService.sendEmail(emailDto, request, response);
		return jwt;
	}

	@Override
	public List<String> getAllUserNames(HttpServletRequest request, HttpServletResponse response)
			throws UserLoginProfileException {
		List<UserLoginProfie> users = repo.findAll();
		List<String> names = users.stream().map(UserLoginProfie::getUsername).collect(Collectors.toList());
		return names ;
	}

	@Override
	public Status verifyOtpForPasswordUpdate(OtpDto dto, HttpServletRequest request, HttpServletResponse response)
			throws UserLoginProfileException {
		if(!helper.isUserExistsByEmail(dto.getEmail())) {
			throw new UserLoginProfileException("User Doesn't exists with email ----> "+dto.getEmail());
		}
		OtpEntity otp = otpEntityRepo.getotpForPasswordChange(dto.getEmail());
		TempPassword pass = tempPasswordRepo.findByEmail(dto.getEmail());
		if (otp==null) {
			throw new UserLoginProfileException("No OTP found , please try again");
		}
		else if(pass==null) {
			throw new UserLoginProfileException("No Record found , please try again");
		}
		else if(otp.getOtp().equals(dto.getOtp())) {
			UserLoginProfie user = repo.findByEmail(dto.getEmail());
			user.setPassword(pass.getPassword());
			return Status.SUCCESS;
		}
		throw new UserLoginProfileException("OTP is invalid");
	}

	@Override
	public Status logout(HttpServletRequest request, HttpServletResponse response)
			throws UserLoginProfileException, UserProfileException {
		 String jwt = helper.parseJwt(request);
	      tokenBlackList.addToBlacklist(jwt);
	      return Status.LOGGED_OUT_SUCCESSFULLY;
	}

	@Override
	@Transactional
	public Status deleteUserByEmail(RequestDto dto, HttpServletRequest request, HttpServletResponse response)
			throws UserLoginProfileException, UserProfileException {
		//this is for temp
		repo.deleteByEmail(dto.getEmail());
		return Status.SUCCESS;
	}
	

}
