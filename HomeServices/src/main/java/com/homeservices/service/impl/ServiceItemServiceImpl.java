package com.homeservices.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homeservices.dto.request.ServiceItemDto;
import com.homeservices.exception.ServiceCategoryException;
import com.homeservices.exception.ServiceItemException;
import com.homeservices.exception.UserProfileException;
import com.homeservices.model.ServiceCategory;
import com.homeservices.model.ServiceItem;
import com.homeservices.repo.ServiceCategoryRepo;
import com.homeservices.repo.ServiceItemRepo;
import com.homeservices.service.ServiceItemService;
import com.homeservices.util.RepoHelper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class ServiceItemServiceImpl implements ServiceItemService{

	public final static Logger logger = LogManager.getLogger(ServiceItemServiceImpl.class);
	
	@Autowired
	private ServiceItemRepo repo;
	
	@Autowired
	private RepoHelper helper;
	
	@Autowired
	private ServiceCategoryRepo categoryRepo;
	
	@Override
	public ServiceItem addServiceItem(ServiceItemDto dto, HttpServletRequest request, HttpServletResponse response)
			throws UserProfileException, ServiceItemException, ServiceCategoryException {
		logger.info("<------ ServiceItemServiceImpl : addServiceItem (BEGIN) with request => {} ------>",dto);
		if(helper.IsSuperAdminOrAdmin(request)) {
			if(categoryRepo.existsById(dto.getCategoryId())) {
				if(!repo.existsByName(dto.getName())) {
					ServiceItem item = ServiceItem.build(dto);
					ServiceCategory cat = categoryRepo.findById(dto.getCategoryId()).get();
					item.setCategory(cat);
					item = repo.save(item);
					logger.info("<------ ServiceItemServiceImpl : addServiceItem (END) with request => {} ------>",item);
					return item;
				}else {
					logger.info("<------ ServiceItemServiceImpl : addServiceItem (FAILED) with reason => {} ------>","Service already exists");
					throw new ServiceItemException("Service already exists");
				}
			}
			else {
				logger.info("<------ ServiceItemServiceImpl : addServiceItem (FAILED) with reason => {} ------>","Category not found");
				throw new ServiceCategoryException("Category not found with id : "+dto.getCategoryId());
			}
		}
		else {
			logger.info("<------ ServiceItemServiceImpl : addServiceItem (FAILED) ------>");
			throw new UserProfileException("Only admin can able to add the Services");
		}
	}

	@Override
	public ServiceItem updateServiceItem(ServiceItemDto dto, HttpServletRequest request, HttpServletResponse response)
			throws UserProfileException, ServiceItemException, ServiceCategoryException {
		logger.info("<------ ServiceItemServiceImpl : updateServiceItem (BEGIN) with request => {} ------>",dto);
		if(helper.IsSuperAdminOrAdmin(request)) {
				if(repo.existsByName(dto.getName())) {
					ServiceItem oldItem = repo.findById(dto.getId()).get();
					ServiceItem item = ServiceItem.build(dto);
					BeanUtils.copyProperties(item, oldItem, helper.getNullPropertyNames(item));
					oldItem = repo.save(item);
					logger.info("<------ ServiceItemServiceImpl : updateServiceItem (END) with request => {} ------>",item);
					return oldItem;
				}else {
					logger.info("<------ ServiceItemServiceImpl : updateServiceItem (FAILED) with reason => {} ------>","Service not exists");
					throw new ServiceItemException("Service not exists");
				}
		}
		else {
			logger.info("<------ ServiceItemServiceImpl : updateServiceItem (FAILED) ------>");
			throw new UserProfileException("Only admin can able to add the Services");
		}
	}

	@Override
	public ServiceItem getServiceItem(Long id, HttpServletRequest request, HttpServletResponse response)
			throws UserProfileException, ServiceItemException, ServiceCategoryException {
		logger.info("<------ ServiceItemServiceImpl : getServiceItem (BEGIN) with request => {} ------>",id);
		if(repo.existsById(id)) {
			ServiceItem oldItem = repo.findById(id).get();
			logger.info("<------ ServiceItemServiceImpl : getServiceItem (END) with response => {} ------>",oldItem);
			return oldItem;
		}else {
			logger.info("<------ ServiceItemServiceImpl : getServiceItem (FAILED) with reason => {} ------>","Service not exists");
			throw new ServiceItemException("Service not exists");
		}
	}

	@Override
	public List<ServiceItem> getAllServiceItems(HttpServletRequest request, HttpServletResponse response)
			throws UserProfileException, ServiceItemException, ServiceCategoryException {
		logger.info("<------ ServiceItemServiceImpl : getAllServiceItems (INSIDE) ------>");
		return repo.findAll();
	}

	@Override
	public List<ServiceItem> getAllServiceItemsByCategoryId(Long id, HttpServletRequest request,
			HttpServletResponse response) throws UserProfileException, ServiceItemException, ServiceCategoryException {
		logger.info("<------ ServiceItemServiceImpl : getAllServiceItemsByCategoryId (INSIDE) ------>");
		return repo.getAllItemsByCategory(id);
	}

	@Override
	public String deleteServiceItem(Long id, HttpServletRequest request, HttpServletResponse response)
			throws UserProfileException, ServiceItemException, ServiceCategoryException {
		logger.info("<------ ServiceItemServiceImpl : deleteServiceItem (BEGIN) with request => {} ------>",id);
		if(helper.IsSuperAdminOrAdmin(request)) {
				if(repo.existsById(id)) {
					repo.deleteById(id);
					logger.info("<------ ServiceItemServiceImpl : deleteServiceItem (END) with response => {} ------>","Deleted Successfully");
					return "Deleted Successfully";
				}else {
					logger.info("<------ ServiceItemServiceImpl : deleteServiceItem (FAILED) with reason => {} ------>","Service not exists");
					throw new ServiceItemException("Service not exists");
				}
		}
		else {
			logger.info("<------ ServiceItemServiceImpl : deleteServiceItem (FAILED) ------>");
			throw new UserProfileException("Only admin can able to add the Services");
		}
	}

}
