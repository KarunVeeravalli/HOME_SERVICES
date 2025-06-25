package com.homeservices.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homeservices.dto.request.ServiceCategoryDto;
import com.homeservices.exception.ServiceCategoryException;
import com.homeservices.exception.UserProfileException;
import com.homeservices.model.ServiceCategory;
import com.homeservices.repo.ServiceCategoryRepo;
import com.homeservices.service.ServiceCategoryService;
import com.homeservices.util.RepoHelper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class ServiceCategoryServiceImpl implements ServiceCategoryService{
	
	@Autowired
	private RepoHelper helper;
	
	@Autowired
	private ServiceCategoryRepo repo;

	public static final Logger logger = LogManager.getLogger(ServiceCategoryServiceImpl.class);
	
	@Override
	public ServiceCategory addServiceCategory(ServiceCategoryDto dto, HttpServletRequest request,
			HttpServletResponse response) throws UserProfileException, ServiceCategoryException {
		logger.info("<------ ServiceCategoryServiceImpl : addServiceCategory (BEGIN) with request => {} ------>",dto);
		if(helper.IsSuperAdminOrAdmin(request)) {
			if(!repo.existsByName(dto.getName())) {
				ServiceCategory item = repo.save(ServiceCategory.build(dto));
				logger.info("<------ ServiceCategoryServiceImpl : addServiceCategory (END) with response => {} ------>",item);
				return item;
			}
			else {
				logger.info("<------ ServiceCategoryServiceImpl : addServiceCategory (FAILED) with reason => {} ------>","Category already present");
				throw new ServiceCategoryException("Category found with name : "+dto.getName()+" Please update it");
			}
		}
		else {
			logger.info("<------ ServiceCategoryServiceImpl : addServiceCategory (FAILED) ------>");
			throw new UserProfileException("Only admin can able to add the Category");
		}
	}

	@Override
	public ServiceCategory updateServiceCategory(ServiceCategoryDto dto, HttpServletRequest request,
			HttpServletResponse response) throws UserProfileException, ServiceCategoryException {
		logger.info("<------ ServiceCategoryServiceImpl : updateServiceCategory (BEGIN) with request => {} ------>",dto);
		if(helper.IsSuperAdminOrAdmin(request)) {
			if(repo.existsByName(dto.getName())) {
				ServiceCategory oldItem = repo.findById(dto.getId()).get();
				ServiceCategory item = ServiceCategory.build(dto);
				BeanUtils.copyProperties(item, oldItem, helper.getNullPropertyNames(item));
				oldItem = repo.save(oldItem);
				logger.info("<------ ServiceCategoryServiceImpl : updateServiceCategory (END) with response => {} ------>",oldItem);
				return oldItem;
			}
			else {
				logger.info("<------ ServiceCategoryServiceImpl : updateServiceCategory (FAILED) with reason => {} ------>","Category not found");
				throw new ServiceCategoryException("Category not found with id : "+dto.getId());
			}
		}
		else {
			logger.info("<------ ServiceCategoryServiceImpl : updateServiceCategory (FAILED) ------>");
			throw new UserProfileException("Only admin can able to add the Category");
		}
	}

	@Override
	public ServiceCategory getServiceCategory(Long id, HttpServletRequest request, HttpServletResponse response)
			throws UserProfileException, ServiceCategoryException {
		logger.info("<------ ServiceCategoryServiceImpl : getServiceCategory (BEGIN) with request => {} ------>",id);
		if(helper.IsSuperAdminOrAdmin(request)) {
			ServiceCategory item = repo.findById(id).get();
			logger.info("<------ ServiceCategoryServiceImpl : getServiceCategory (END) with response => {} ------>",item);
			return item;
		}else {
			logger.info("<------ ServiceCategoryServiceImpl : getServiceCategory (FAILED) ------>");
			throw new UserProfileException("Only admin can able to add the Category");
		}
	}

	@Override
	public List<ServiceCategory> getAllServiceCategory(HttpServletRequest request, HttpServletResponse response)
			throws UserProfileException, ServiceCategoryException {
		logger.info("<------ ServiceCategoryServiceImpl : getAllServiceCategory (BEGIN) ------>");
		if(helper.IsSuperAdminOrAdmin(request)) {
			List<ServiceCategory> items = repo.findAll();
			logger.info("<------ ServiceCategoryServiceImpl : getAllServiceCategory (END) with response => {} ------>",items);
			return items;
		}else {
			logger.info("<------ ServiceCategoryServiceImpl : getAllServiceCategory (FAILED) ------>");
			throw new UserProfileException("Only admin can able to add the Category");
		}
	}

	@Override
	public String deleteServiceCategory(Long id, HttpServletRequest request, HttpServletResponse response)
			throws UserProfileException, ServiceCategoryException {
		logger.info("<------ ServiceCategoryServiceImpl : deleteServiceCategory (BEGIN) ------>");
		if(helper.IsSuperAdminOrAdmin(request)) {
			if(repo.existsById(id)) {
				repo.deleteById(id);
				logger.info("<------ ServiceCategoryServiceImpl : deleteServiceCategory (END) ------>");
				return "Successfully DELETED";
			}else {
				logger.info("<------ ServiceCategoryServiceImpl : deleteServiceCategory (FAILED) with reason => {} ------>","Category not found");
				throw new ServiceCategoryException("Category not found with id : "+id);
			}
		}else {
			logger.info("<------ ServiceCategoryServiceImpl : deleteServiceCategory (FAILED) ------>");
			throw new UserProfileException("Only admin can able to add the Category");
		}
	}

}
