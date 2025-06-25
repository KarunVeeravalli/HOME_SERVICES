package com.homeservices.service;

import java.util.List;

import com.homeservices.dto.request.ServiceCategoryDto;
import com.homeservices.exception.ServiceCategoryException;
import com.homeservices.exception.UserProfileException;
import com.homeservices.model.ServiceCategory;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface ServiceCategoryService {
	
	public ServiceCategory addServiceCategory(ServiceCategoryDto dto, HttpServletRequest request, HttpServletResponse response) throws UserProfileException, ServiceCategoryException;
	public ServiceCategory updateServiceCategory(ServiceCategoryDto dto, HttpServletRequest request, HttpServletResponse response) throws UserProfileException, ServiceCategoryException;
	public ServiceCategory getServiceCategory(Long id, HttpServletRequest request, HttpServletResponse response) throws UserProfileException, ServiceCategoryException;
	public List<ServiceCategory> getAllServiceCategory( HttpServletRequest request, HttpServletResponse response) throws UserProfileException, ServiceCategoryException;
	public String deleteServiceCategory(Long id,HttpServletRequest request, HttpServletResponse response) throws UserProfileException, ServiceCategoryException;
}
