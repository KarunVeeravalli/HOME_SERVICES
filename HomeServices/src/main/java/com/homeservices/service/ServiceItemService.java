package com.homeservices.service;

import java.util.List;

import com.homeservices.dto.request.ServiceItemDto;
import com.homeservices.exception.ServiceCategoryException;
import com.homeservices.exception.ServiceItemException;
import com.homeservices.exception.UserProfileException;
import com.homeservices.model.ServiceItem;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface ServiceItemService {

	public ServiceItem addServiceItem(ServiceItemDto dto, HttpServletRequest request, HttpServletResponse response)
			throws UserProfileException, ServiceItemException, ServiceCategoryException;

	public ServiceItem updateServiceItem(ServiceItemDto dto, HttpServletRequest request, HttpServletResponse response)
			throws UserProfileException, ServiceItemException, ServiceCategoryException;

	public ServiceItem getServiceItem(Long id, HttpServletRequest request, HttpServletResponse response)
			throws UserProfileException, ServiceItemException, ServiceCategoryException;

	public List<ServiceItem> getAllServiceItems(HttpServletRequest request, HttpServletResponse response)
			throws UserProfileException, ServiceItemException, ServiceCategoryException;

	public List<ServiceItem> getAllServiceItemsByCategoryId(Long id, HttpServletRequest request,
			HttpServletResponse response) throws UserProfileException, ServiceItemException, ServiceCategoryException;

	public String deleteServiceItem(Long id, HttpServletRequest request, HttpServletResponse response)
			throws UserProfileException, ServiceItemException, ServiceCategoryException;
}
