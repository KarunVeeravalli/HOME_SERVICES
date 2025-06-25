package com.homeservices.service;

import java.util.List;

import com.homeservices.dto.request.TechnicianProfileDto;
import com.homeservices.exception.ServiceCategoryException;
import com.homeservices.exception.TechnicianProfileException;
import com.homeservices.exception.UserLoginProfileException;
import com.homeservices.exception.UserProfileException;
import com.homeservices.model.TechnicianProfile;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface TechnicianProfileService {	
	
	public TechnicianProfile addTechnician(TechnicianProfileDto dto, HttpServletRequest request, HttpServletResponse response) throws TechnicianProfileException, UserProfileException, ServiceCategoryException, UserLoginProfileException;
	public TechnicianProfile updateTechnician(TechnicianProfileDto dto, HttpServletRequest request, HttpServletResponse response) throws TechnicianProfileException, UserProfileException, ServiceCategoryException;
	public TechnicianProfile getTechnician(Long id, HttpServletRequest request, HttpServletResponse response) throws TechnicianProfileException, UserProfileException, ServiceCategoryException;
	public List<TechnicianProfile> getAllTechnician(HttpServletRequest request, HttpServletResponse response) throws TechnicianProfileException, UserProfileException, ServiceCategoryException;
	public List<TechnicianProfile> getAllTechnicianAvialable(HttpServletRequest request, HttpServletResponse response) throws TechnicianProfileException, UserProfileException, ServiceCategoryException;
	public String deleteTechnician(Long id,HttpServletRequest request, HttpServletResponse response )throws TechnicianProfileException, UserProfileException, ServiceCategoryException;

}
