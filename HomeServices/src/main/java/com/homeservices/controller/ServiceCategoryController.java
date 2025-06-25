package com.homeservices.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.homeservices.dto.common.CRequest;
import com.homeservices.dto.common.Header;
import com.homeservices.dto.request.ServiceCategoryDto;
import com.homeservices.dto.response.GeneralResponse;
import com.homeservices.exception.ServiceCategoryException;
import com.homeservices.exception.UserProfileException;
import com.homeservices.model.ServiceCategory;
import com.homeservices.service.ServiceCategoryService;
import com.homeservices.util.RepoHelper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/service-category")
public class ServiceCategoryController {

    public static final Logger logger = LogManager.getLogger(ServiceCategoryController.class);

    @Autowired
    private RepoHelper helper;

    @Autowired
    private ServiceCategoryService service;

    @PostMapping("/addServiceCategory")
    public ResponseEntity<GeneralResponse> addServiceCategory(@RequestBody String dto, HttpServletRequest request, HttpServletResponse response)
            throws ServiceCategoryException, UserProfileException {
        GeneralResponse gRes = new GeneralResponse();
        Header header = helper.getHeader(dto);
        gRes.setHeader(header);
        logger.info("<------ ServiceCategoryController : addServiceCategory (BEGIN) ------>");
        try {
            ServiceCategoryDto req = helper.string2Object(dto, ServiceCategoryDto.class);
            ServiceCategory res = service.addServiceCategory(req, request, response);
            gRes.setData(helper.object2String(res));
            gRes.setResponseCode(200);
            logger.info("<------ ServiceCategoryController : addServiceCategory (END) ------>");
            return ResponseEntity.ok(gRes);
        } catch (Exception e) {
            gRes.setExceptionMsg(e.getMessage());
            gRes.setResponseCode(400);
            logger.error("<------ ServiceCategoryController : addServiceCategory (FAILED) ------>", e);
            return new ResponseEntity<>(gRes, HttpStatus.BAD_REQUEST);
        }
    }
    
    @PostMapping("/updateServiceCategory")
    public ResponseEntity<GeneralResponse> updateServiceCategory(@RequestBody String dto, HttpServletRequest request, HttpServletResponse response)
            throws ServiceCategoryException, UserProfileException {
        GeneralResponse gRes = new GeneralResponse();
        Header header = helper.getHeader(dto);
        gRes.setHeader(header);
        logger.info("<------ ServiceCategoryController : updateServiceCategory (BEGIN) ------>");
        try {
            ServiceCategoryDto req = helper.string2Object(dto, ServiceCategoryDto.class);
            ServiceCategory res = service.updateServiceCategory(req, request, response);
            gRes.setData(helper.object2String(res));
            gRes.setResponseCode(200);
            logger.info("<------ ServiceCategoryController : updateServiceCategory (END) ------>");
            return ResponseEntity.ok(gRes);
        } catch (Exception e) {
            gRes.setExceptionMsg(e.getMessage());
            gRes.setResponseCode(400);
            logger.error("<------ ServiceCategoryController : updateServiceCategory (FAILED) ------>", e);
            return new ResponseEntity<>(gRes, HttpStatus.BAD_REQUEST);
        }
    }
    
    @PostMapping("/getServiceCategory")
    public ResponseEntity<GeneralResponse> getServiceCategory(@RequestBody String dto, HttpServletRequest request, HttpServletResponse response)
            throws ServiceCategoryException, UserProfileException {
        GeneralResponse gRes = new GeneralResponse();
        Header header = helper.getHeader(dto);
        gRes.setHeader(header);
        logger.info("<------ ServiceCategoryController : getServiceCategory (BEGIN) ------>");
        try {
            CRequest req = helper.string2Object(dto, CRequest.class);
            ServiceCategory res = service.getServiceCategory(req.getId(), request, response);
            gRes.setData(helper.object2String(res));
            gRes.setResponseCode(200);
            logger.info("<------ ServiceCategoryController : getServiceCategory (END) ------>");
            return ResponseEntity.ok(gRes);
        } catch (Exception e) {
            gRes.setExceptionMsg(e.getMessage());
            gRes.setResponseCode(400);
            logger.error("<------ ServiceCategoryController : getServiceCategory (FAILED) ------>", e);
            return new ResponseEntity<>(gRes, HttpStatus.BAD_REQUEST);
        }
    }
    
    @PostMapping("/getAllServiceCategory")
    public ResponseEntity<GeneralResponse> getAllServiceCategory(@RequestBody String dto, HttpServletRequest request, HttpServletResponse response)
            throws ServiceCategoryException, UserProfileException {
        GeneralResponse gRes = new GeneralResponse();
        Header header = helper.getHeader(dto);
        gRes.setHeader(header);
        logger.info("<------ ServiceCategoryController : getAllServiceCategory (BEGIN) ------>");
        try {
            List<ServiceCategory> res = service.getAllServiceCategory(request, response);
            gRes.setData(helper.object2String(res));
            gRes.setResponseCode(200);
            logger.info("<------ ServiceCategoryController : getAllServiceCategory (END) ------>");
            return ResponseEntity.ok(gRes);
        } catch (Exception e) {
            gRes.setExceptionMsg(e.getMessage());
            gRes.setResponseCode(400);
            logger.error("<------ ServiceCategoryController : getAllServiceCategory (FAILED) ------>", e);
            return new ResponseEntity<>(gRes, HttpStatus.BAD_REQUEST);
        }
    }
    
    @PostMapping("/deleteServiceCategory")
    public ResponseEntity<GeneralResponse> deleteServiceCategory(@RequestBody String dto, HttpServletRequest request, HttpServletResponse response)
            throws ServiceCategoryException, UserProfileException {
        GeneralResponse gRes = new GeneralResponse();
        Header header = helper.getHeader(dto);
        gRes.setHeader(header);
        logger.info("<------ ServiceCategoryController : deleteServiceCategory (BEGIN) ------>");
        try {
            CRequest req = helper.string2Object(dto, CRequest.class);
            String res = service.deleteServiceCategory(req.getId(), request, response);
            gRes.setData(helper.object2String(res));
            gRes.setResponseCode(200);
            logger.info("<------ ServiceCategoryController : deleteServiceCategory (END) ------>");
            return ResponseEntity.ok(gRes);
        } catch (Exception e) {
            gRes.setExceptionMsg(e.getMessage());
            gRes.setResponseCode(400);
            logger.error("<------ ServiceCategoryController : deleteServiceCategory (FAILED) ------>", e);
            return new ResponseEntity<>(gRes, HttpStatus.BAD_REQUEST);
        }
    }
}
