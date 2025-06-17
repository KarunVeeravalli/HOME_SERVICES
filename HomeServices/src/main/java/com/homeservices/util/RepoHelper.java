package com.homeservices.util;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Base64;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.homeservices.dto.common.MyUser;
import com.homeservices.dto.response.GeneralResponse;
import com.homeservices.enums.URole;
import com.homeservices.model.Role;
import com.homeservices.model.UserProfile;
import com.homeservices.repo.RoleRepo;
import com.homeservices.repo.UnAuthUserRepo;
import com.homeservices.repo.UserProfileRepo;
import com.homeservices.security.util.JwtUtils;

import jakarta.servlet.http.HttpServletRequest;

@Component
public class RepoHelper {
	
	 @Value("${homeservices.app.sendermail}")
	  private String fromEmail;
	 
	 @Value("${homeservices.req-res.encryption}")
	 private String encryptedFlag;
	 
		@Value("${homeservices.encrypted.secret}")
		private String secretKey;		 
	 
	 public String getMail() {
		 return fromEmail;
	 }
	 
	@Autowired
	JwtUtils jwtService;

	@Autowired
	UserProfileRepo userProfileRepository;

	@Autowired
	RoleRepo roleRepository;
	
	@Autowired
	UnAuthUserRepo unAuthUserRepo;
	
	public Object getResponse(GeneralResponse res) throws Exception {
		
		if(encryptedFlag.equals("true")) {
			String result = new String();
			result = encryptAES(res.toString(), secretKey);
//			result = Base64.getEncoder().encodeToString((res.toString()+secretKey).getBytes());
			return result;
		}else {
			return res;
		}
	}
	
//	public Object getRequest(String req,O type ) throws Exception{
//		if(encryptedFlag.equals("true")) {
//			SignupRequest dto = helper.string2Object(signupRequest, SignupRequest.class);
//		}
//	}
	
private final ObjectMapper mapper = new ObjectMapper();
	
	
	
	public  <T> T string2Object(String req, Class<T> type) {
		try {
			if(encryptedFlag.equals("true")) {
				req = decryptAES(req, secretKey);
			}
			return mapper.readValue(req, type);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new RuntimeException("Some error while decoding");
		}
	}	
	
	
	public  String object2String(Object req) {
		try {
			String jsonString = mapper.writeValueAsString(req);
			if(encryptedFlag.equals("true")) {
				jsonString = encryptAES(jsonString, secretKey);
				return jsonString;
			}
			return req.toString();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new RuntimeException("Some error while Encoding");
		}
	}	
	
	
	
	public static String encryptAES(String data, String secretKey) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        byte[] encrypted = cipher.doFinal(data.getBytes());

        return Base64.getEncoder().encodeToString(encrypted);
    }
	public static String decryptAES(String encryptedData, String secretKey) throws Exception {
	    SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes(), "AES");
	    Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
	    cipher.init(Cipher.DECRYPT_MODE, keySpec);
	    byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
	    return new String(decrypted);
	}
	
	public Boolean isTempUserExists(String email) {
		return unAuthUserRepo.existsByEmail(email);
	}

	public String parseJwt(HttpServletRequest request) {
		String headerAuth = request.getHeader("Authorization");

		if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
			return headerAuth.substring(7);
		}

		return null;
	}

	public String getUsernameFromToken(HttpServletRequest request) {
		Enumeration<String> authHeader = request.getHeaders(HttpHeaders.AUTHORIZATION);
		String token = (authHeader.nextElement());
		if (token != null && token.startsWith("Bearer ")) {
			token = token.substring(7);
		}
		String username = jwtService.getUserNameFromJwtToken(token);
		return username;
	}

	public String[] getNullPropertyNames(Object source) {
		final BeanWrapper src = new BeanWrapperImpl(source);
		java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

		Set<String> emptyNames = new HashSet<>();
		for (java.beans.PropertyDescriptor pd : pds) {
			Object srcValue = src.getPropertyValue(pd.getName());
			if (srcValue == null)
				emptyNames.add(pd.getName());
		}

		String[] result = new String[emptyNames.size()];
		return emptyNames.toArray(result);
	}

	public LocalTime findTimeBetweenTimestamps(LocalTime localTime, LocalTime localTime2) {

		Duration duration = Duration.between(localTime, localTime2);

		int hours = (int) duration.toHoursPart();
		int minutes = (int) duration.toMinutesPart();
		int seconds = (int) duration.toSecondsPart();

		return LocalTime.of(hours, minutes, seconds);
	}

	public Boolean isUserExists(HttpServletRequest request) {
		UserProfile profile = userProfileRepository.getUserByEmail(getUsernameFromToken(request));
		return profile != null ? true : false;
	}
	
	public Boolean isUserExistsByEmail(String emial) {
		UserProfile profile = userProfileRepository.getUserByEmail(emial);
		return profile != null ? true : false;
	}

	public UserProfile getUserProfile(HttpServletRequest request) {
		UserProfile profile = userProfileRepository.getUserByEmail(getUsernameFromToken(request));
		return profile;
	}

	public Boolean IsUserAdmin(HttpServletRequest request) {
		UserProfile profile = userProfileRepository.getUserByEmail(getUsernameFromToken(request));
		Role adminRole = roleRepository.findByName(URole.ROLE_ADMIN);
		if (profile.getRoles().contains(adminRole)) {
			return true;
		}
		return false;
	}
	
	public Boolean IsSuperAdmin(HttpServletRequest request) {
		UserProfile profile = userProfileRepository.getUserByEmail(getUsernameFromToken(request));
		Role adminRole = roleRepository.findByName(URole.ROLE_SUPERADMIN);
		if (profile.getRoles().contains(adminRole)) {
			return true;
		}
		return false;
	}
	
	public Boolean IsSuperAdminOrAdmin(HttpServletRequest request) {
		UserProfile profile = userProfileRepository.getUserByEmail(getUsernameFromToken(request));
		Role adminRole = roleRepository.findByName(URole.ROLE_SUPERADMIN);
		Role adminRole1 = roleRepository.findByName(URole.ROLE_ADMIN);
		if (profile.getRoles().contains(adminRole)|| profile.getRoles().contains(adminRole1)) {
			return true;
		}
		return false;
	}

	public String getOtp() {
		Integer upper = 999999;
		Integer lower = 111111;
		Integer otp = (int) (Math.random() * (upper - lower)) + lower;
		return otp.toString();
	}

	public String getOtpAndMsg(String otp) {

		System.out.println(otp);
		String body = "Dear Customer,\n" + "\n" + "\n" + "\n" + "\n" + "\n" + " " + otp
				+ " is your SECRET OTP for Accessing our E-com Application at Browser on " + LocalDate.now()
				+ ",  OTP valid for 5 mins.Pls do not share OTP with anyone.\n" + "\n" + "\n" + "\n"
				+ "In case you have any queries / clarifications, please call us at our Customer Service number :\n"
				+ "\n" + "8340018900\n" + "8340018900\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n"
				+ " Thank You For Using our E-com Application :) ";

		return body;
	}

	public String getLoginMsg(String mail) {

		System.out.println(mail);

		String body = "Dear Customer,\n" + "\n" + "\n" + "\n" + "\n" + "\n" + " "
				+ "There's been a new sign-in to your E-com Application account associated with the email address "
				+ "\n" + mail + ",  on " + LocalDateTime.now() + ".\n" + "\n"
				+ "If this wasn't you, you need to change your E-com Application account password to protect your account."
				+ "\n" + "\n" + "http://localhost:8080/api/auth/changePassword to change password"

				+ "In case you have any queries / clarifications, please call us at our Customer Service number :\n"
				+ "\n" + "8340018900\n" + "8340018900\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "\n"
				+ " Thank You For Using our E-com Application :) ";

		return body;
	}
	
	
	public static MyUser getUser() {
		MyUser user = null;
		Object principal = SecurityContextHolder.getContext()!=null?SecurityContextHolder.getContext().getAuthentication().getPrincipal():null;
		user = (MyUser) principal;
		if(principal != null && !principal.toString().equalsIgnoreCase("anonymousUser")) {
			user = ((MyUser) principal);
		}
		return user;
	}
}
