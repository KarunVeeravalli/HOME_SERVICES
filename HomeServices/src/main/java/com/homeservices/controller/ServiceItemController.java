package com.homeservices.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.homeservices.dto.common.CRequest;
import com.homeservices.dto.common.Header;
import com.homeservices.dto.request.ServiceItemDto;
import com.homeservices.dto.response.GeneralResponse;
import com.homeservices.exception.ServiceCategoryException;
import com.homeservices.exception.ServiceItemException;
import com.homeservices.exception.UserProfileException;
import com.homeservices.model.ServiceItem;
import com.homeservices.service.ServiceItemService;
import com.homeservices.util.RepoHelper;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/service-item")
public class ServiceItemController {

    public static final Logger logger = LogManager.getLogger(ServiceItemController.class);

    @Autowired
    private RepoHelper helper;

    @Autowired
    private ServiceItemService service;
    
    @PostMapping("/addServiceItem")
    public ResponseEntity<GeneralResponse> addServiceItem(@RequestBody String dto, HttpServletRequest request, HttpServletResponse response)
            throws ServiceItemException, UserProfileException, ServiceCategoryException {
        GeneralResponse gRes = new GeneralResponse();
        Header header = helper.getHeader(dto);
        gRes.setHeader(header);
        logger.info("<------ ServiceItemController : addServiceItem (BEGIN) ------>");
        try {
            ServiceItemDto req = helper.string2Object(dto, ServiceItemDto.class);
            ServiceItem res = service.addServiceItem(req, request, response);
            gRes.setData(helper.object2String(res));
            gRes.setResponseCode(200);
            logger.info("<------ ServiceItemController : addServiceItem (END) ------>");
            return ResponseEntity.ok(gRes);
        } catch (Exception e) {
            gRes.setExceptionMsg(e.getMessage());
            gRes.setResponseCode(400);
            logger.error("<------ ServiceItemController : addServiceItem (FAILED) ------>", e);
            return new ResponseEntity<>(gRes, HttpStatus.BAD_REQUEST);
        }
    }
    
    @PostMapping("/updateServiceItem")
    public ResponseEntity<GeneralResponse> updateServiceItem(@RequestBody String dto, HttpServletRequest request, HttpServletResponse response)
            throws ServiceItemException, UserProfileException, ServiceCategoryException {
        GeneralResponse gRes = new GeneralResponse();
        Header header = helper.getHeader(dto);
        gRes.setHeader(header);
        logger.info("<------ ServiceItemController : updateServiceItem (BEGIN) ------>");
        try {
            ServiceItemDto req = helper.string2Object(dto, ServiceItemDto.class);
            ServiceItem res = service.updateServiceItem(req, request, response);
            gRes.setData(helper.object2String(res));
            gRes.setResponseCode(200);
            logger.info("<------ ServiceItemController : updateServiceItem (END) ------>");
            return ResponseEntity.ok(gRes);
        } catch (Exception e) {
            gRes.setExceptionMsg(e.getMessage());
            gRes.setResponseCode(400);
            logger.error("<------ ServiceItemController : updateServiceItem (FAILED) ------>", e);
            return new ResponseEntity<>(gRes, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/getServiceItem")
    public ResponseEntity<GeneralResponse> getServiceItem(@RequestBody String dto, HttpServletRequest request, HttpServletResponse response)
            throws ServiceItemException, UserProfileException, ServiceCategoryException {
        GeneralResponse gRes = new GeneralResponse();
        Header header = helper.getHeader(dto);
        gRes.setHeader(header);
        logger.info("<------ ServiceItemController : getServiceItem (BEGIN) ------>");
        try {
            CRequest req = helper.string2Object(dto, CRequest.class);
            ServiceItem res = service.getServiceItem(req.getId(), request, response);
            gRes.setData(helper.object2String(res));
            gRes.setResponseCode(200);
            logger.info("<------ ServiceItemController : getServiceItem (END) ------>");
            return ResponseEntity.ok(gRes);
        } catch (Exception e) {
            gRes.setExceptionMsg(e.getMessage());
            gRes.setResponseCode(400);
            logger.error("<------ ServiceItemController : getServiceItem (FAILED) ------>", e);
            return new ResponseEntity<>(gRes, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/getAllServiceItems")
    public ResponseEntity<GeneralResponse> getAllServiceItems(@RequestBody String dto, HttpServletRequest request, HttpServletResponse response)
            throws ServiceItemException, UserProfileException, ServiceCategoryException {
        GeneralResponse gRes = new GeneralResponse();
        Header header = helper.getHeader(dto);
        gRes.setHeader(header);
        logger.info("<------ ServiceItemController : getAllServiceItems (BEGIN) ------>");
        try {
            List<ServiceItem> res = service.getAllServiceItems(request, response);
            gRes.setData(helper.object2String(res));
            gRes.setResponseCode(200);
            logger.info("<------ ServiceItemController : getAllServiceItems (END) ------>");
            return ResponseEntity.ok(gRes);
        } catch (Exception e) {
            gRes.setExceptionMsg(e.getMessage());
            gRes.setResponseCode(400);
            logger.error("<------ ServiceItemController : getAllServiceItems (FAILED) ------>", e);
            return new ResponseEntity<>(gRes, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/getAllServiceItemsByCategoryId")
    public ResponseEntity<GeneralResponse> getAllServiceItemsByCategoryId(@RequestBody String dto, HttpServletRequest request, HttpServletResponse response)
            throws ServiceItemException, UserProfileException, ServiceCategoryException {
        GeneralResponse gRes = new GeneralResponse();
        Header header = helper.getHeader(dto);
        gRes.setHeader(header);
        logger.info("<------ ServiceItemController : getAllServiceItemsByCategoryId (BEGIN) ------>");
        try {
            CRequest req = helper.string2Object(dto, CRequest.class);
            List<ServiceItem> res = service.getAllServiceItemsByCategoryId(req.getId(), request, response);
            gRes.setData(helper.object2String(res));
            gRes.setResponseCode(200);
            logger.info("<------ ServiceItemController : getAllServiceItemsByCategoryId (END) ------>");
            return ResponseEntity.ok(gRes);
        } catch (Exception e) {
            gRes.setExceptionMsg(e.getMessage());
            gRes.setResponseCode(400);
            logger.error("<------ ServiceItemController : getAllServiceItemsByCategoryId (FAILED) ------>", e);
            return new ResponseEntity<>(gRes, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/deleteServiceItem")
    public ResponseEntity<GeneralResponse> deleteServiceItem(@RequestBody String dto, HttpServletRequest request, HttpServletResponse response)
            throws ServiceItemException, UserProfileException, ServiceCategoryException {
        GeneralResponse gRes = new GeneralResponse();
        Header header = helper.getHeader(dto);
        gRes.setHeader(header);
        logger.info("<------ ServiceItemController : deleteServiceItem (BEGIN) ------>");
        try {
            CRequest req = helper.string2Object(dto, CRequest.class);
            String res = service.deleteServiceItem(req.getId(), request, response);
            gRes.setData(helper.object2String(res));
            gRes.setResponseCode(200);
            logger.info("<------ ServiceItemController : deleteServiceItem (END) ------>");
            return ResponseEntity.ok(gRes);
        } catch (Exception e) {
            gRes.setExceptionMsg(e.getMessage());
            gRes.setResponseCode(400);
            logger.error("<------ ServiceItemController : deleteServiceItem (FAILED) ------>", e);
            return new ResponseEntity<>(gRes, HttpStatus.BAD_REQUEST);
        }
    }
}
