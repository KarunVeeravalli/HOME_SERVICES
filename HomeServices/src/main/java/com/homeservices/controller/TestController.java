package com.homeservices.controller;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.homeservices.exception.UserLoginProfileException;
import com.homeservices.service.UserLoginProfileService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/test")
public class TestController {
	@Autowired
	private UserLoginProfileService service;
	
	org.slf4j.Logger logger = LoggerFactory.getLogger(TestController.class);
	
	@GetMapping("/na")
	public List<String> getMethodName(HttpServletRequest request, HttpServletResponse response) throws UserLoginProfileException {
		try {
			System.out.println("inside the project");
			System.out.println(service.getAllUserNames(request, response));
			logger.info("inside the project");
			return service.getAllUserNames(request, response);
			
		} catch (Exception e) {
			System.out.println(e);
		} 
		return null;
	}
	
}
