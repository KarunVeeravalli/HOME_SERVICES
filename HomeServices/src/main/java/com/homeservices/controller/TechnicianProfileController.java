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
import com.homeservices.dto.request.TechnicianProfileDto;
import com.homeservices.dto.response.GeneralResponse;
import com.homeservices.exception.ServiceCategoryException;
import com.homeservices.exception.TechnicianProfileException;
import com.homeservices.exception.UserLoginProfileException;
import com.homeservices.exception.UserProfileException;
import com.homeservices.model.TechnicianProfile;
import com.homeservices.service.TechnicianProfileService;
import com.homeservices.util.RepoHelper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/technician")
public class TechnicianProfileController {

    private static final Logger logger = LogManager.getLogger(TechnicianProfileController.class);

    @Autowired
    private TechnicianProfileService service;

    @Autowired
    private RepoHelper helper;

    @PostMapping("/addTechnician")
    public ResponseEntity<GeneralResponse> addTechnician(@RequestBody String dto, HttpServletRequest request, HttpServletResponse response)
            throws TechnicianProfileException, UserProfileException, ServiceCategoryException, UserLoginProfileException {
        GeneralResponse gRes = new GeneralResponse();
        Header header = helper.getHeader(dto);
        gRes.setHeader(header);
        logger.info("<------ TechnicianProfileController : addTechnician (BEGIN) ------>");
        try {
            TechnicianProfileDto req = helper.string2Object(dto, TechnicianProfileDto.class);
            TechnicianProfile result = service.addTechnician(req, request, response);
            gRes.setData(helper.object2String(result));
            gRes.setResponseCode(200);
            logger.info("<------ TechnicianProfileController : addTechnician (END) ------>");
            return ResponseEntity.ok(gRes);
        } catch (Exception e) {
            gRes.setExceptionMsg(e.getMessage());
            gRes.setResponseCode(400);
            logger.error("<------ TechnicianProfileController : addTechnician (FAILED) {} ------>", e.getMessage());
            return new ResponseEntity<>(gRes, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/updateTechnician")
    public ResponseEntity<GeneralResponse> updateTechnician(@RequestBody String dto, HttpServletRequest request, HttpServletResponse response)
            throws TechnicianProfileException, UserProfileException, ServiceCategoryException {
        GeneralResponse gRes = new GeneralResponse();
        Header header = helper.getHeader(dto);
        gRes.setHeader(header);
        logger.info("<------ TechnicianProfileController : updateTechnician (BEGIN) ------>");
        try {
            TechnicianProfileDto req = helper.string2Object(dto, TechnicianProfileDto.class);
            TechnicianProfile result = service.updateTechnician(req, request, response);
            gRes.setData(helper.object2String(result));
            gRes.setResponseCode(200);
            logger.info("<------ TechnicianProfileController : updateTechnician (END) ------>");
            return ResponseEntity.ok(gRes);
        } catch (Exception e) {
            gRes.setExceptionMsg(e.getMessage());
            gRes.setResponseCode(400);
            logger.error("<------ TechnicianProfileController : updateTechnician (FAILED) {} ------>", e.getMessage());
            return new ResponseEntity<>(gRes, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/getTechnician")
    public ResponseEntity<GeneralResponse> getTechnician(@RequestBody String dto, HttpServletRequest request, HttpServletResponse response)
            throws TechnicianProfileException, UserProfileException, ServiceCategoryException {
        GeneralResponse gRes = new GeneralResponse();
        Header header = helper.getHeader(dto);
        gRes.setHeader(header);
        logger.info("<------ TechnicianProfileController : getTechnician (BEGIN) ------>");
        try {
            CRequest req = helper.string2Object(dto, CRequest.class);
            TechnicianProfile result = service.getTechnician(req.getId(), request, response);
            gRes.setData(helper.object2String(result));
            gRes.setResponseCode(200);
            logger.info("<------ TechnicianProfileController : getTechnician (END) ------>");
            return ResponseEntity.ok(gRes);
        } catch (Exception e) {
            gRes.setExceptionMsg(e.getMessage());
            gRes.setResponseCode(400);
            logger.error("<------ TechnicianProfileController : getTechnician (FAILED) {} ------>", e.getMessage());
            return new ResponseEntity<>(gRes, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/getAllTechnician")
    public ResponseEntity<GeneralResponse> getAllTechnician(@RequestBody String dto, HttpServletRequest request, HttpServletResponse response)
            throws TechnicianProfileException, UserProfileException, ServiceCategoryException {
        GeneralResponse gRes = new GeneralResponse();
        Header header = helper.getHeader(dto);
        gRes.setHeader(header);
        logger.info("<------ TechnicianProfileController : getAllTechnician (BEGIN) ------>");
        try {
            List<TechnicianProfile> result = service.getAllTechnician(request, response);
            gRes.setData(helper.object2String(result));
            gRes.setResponseCode(200);
            logger.info("<------ TechnicianProfileController : getAllTechnician (END) ------>");
            return ResponseEntity.ok(gRes);
        } catch (Exception e) {
            gRes.setExceptionMsg(e.getMessage());
            gRes.setResponseCode(400);
            logger.error("<------ TechnicianProfileController : getAllTechnician (FAILED) {} ------>", e.getMessage());
            return new ResponseEntity<>(gRes, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/getAllTechnicianAvialable")
    public ResponseEntity<GeneralResponse> getAllTechnicianAvialable(@RequestBody String dto, HttpServletRequest request, HttpServletResponse response)
            throws TechnicianProfileException, UserProfileException, ServiceCategoryException {
        GeneralResponse gRes = new GeneralResponse();
        Header header = helper.getHeader(dto);
        gRes.setHeader(header);
        logger.info("<------ TechnicianProfileController : getAllTechnicianAvialable (BEGIN) ------>");
        try {
            List<TechnicianProfile> result = service.getAllTechnicianAvialable(request, response);
            gRes.setData(helper.object2String(result));
            gRes.setResponseCode(200);
            logger.info("<------ TechnicianProfileController : getAllTechnicianAvialable (END) ------>");
            return ResponseEntity.ok(gRes);
        } catch (Exception e) {
            gRes.setExceptionMsg(e.getMessage());
            gRes.setResponseCode(400);
            logger.error("<------ TechnicianProfileController : getAllTechnicianAvialable (FAILED) {} ------>", e.getMessage());
            return new ResponseEntity<>(gRes, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/deleteTechnician")
    public ResponseEntity<GeneralResponse> deleteTechnician(@RequestBody String dto, HttpServletRequest request, HttpServletResponse response)
            throws TechnicianProfileException, UserProfileException, ServiceCategoryException {
        GeneralResponse gRes = new GeneralResponse();
        Header header = helper.getHeader(dto);
        gRes.setHeader(header);
        logger.info("<------ TechnicianProfileController : deleteTechnician (BEGIN) ------>");
        try {
            CRequest req = helper.string2Object(dto, CRequest.class);
            String result = service.deleteTechnician(req.getId(), request, response);
            gRes.setData(helper.object2String(result));
            gRes.setResponseCode(200);
            logger.info("<------ TechnicianProfileController : deleteTechnician (END) ------>");
            return ResponseEntity.ok(gRes);
        } catch (Exception e) {
            gRes.setExceptionMsg(e.getMessage());
            gRes.setResponseCode(400);
            logger.error("<------ TechnicianProfileController : deleteTechnician (FAILED) {} ------>", e.getMessage());
            return new ResponseEntity<>(gRes, HttpStatus.BAD_REQUEST);
        }
    }
}