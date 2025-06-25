package com.homeservices.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.homeservices.dto.common.CRequest;
import com.homeservices.dto.common.Header;
import com.homeservices.dto.request.AddressDto;
import com.homeservices.dto.response.GeneralResponse;
import com.homeservices.exception.AddressException;
import com.homeservices.exception.UserProfileException;
import com.homeservices.model.Address;
import com.homeservices.service.AddressService;
import com.homeservices.util.RepoHelper;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/address")
public class AddressController {
	
	public static final Logger logger = LogManager.getLogger(AddressController.class);
	
	@Autowired
	private RepoHelper helper;
	
	@Autowired
	private AddressService service;
	
	@PostMapping("/addAddress")
	public ResponseEntity<GeneralResponse> addAddress( @RequestBody String dto, HttpServletRequest request, HttpServletResponse response)
			throws AddressException, UserProfileException {
		GeneralResponse gRes = new GeneralResponse();
		Header header = helper.getHeader(dto);
		gRes.setHeader(header);
		logger.info("<------ AddressController : addAddress (BEGIN) ------>");
		try {
			AddressDto req = helper.string2Object(dto, AddressDto.class);
			logger.info("user's tracking id is : {}", header.getTrackingId());
			Address res = service.addAddress(req, request, response);
			gRes.setData(helper.object2String(res));
			gRes.setResponseCode(200);
			logger.info("<------ AddressController : addAddress (END) ------>");
			gRes.setHeader(header);
			return ResponseEntity.ok(gRes);
		} catch (Exception e) {
			gRes.setExceptionMsg(e.getMessage());
//			gRes.getExceptions().add(e);
			gRes.setResponseCode(400);
			logger.info("<------ AddressController : addAddress (FAILED) ------>");
			return new ResponseEntity<>(gRes,HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/updateAddress")
	public ResponseEntity<GeneralResponse> updateAddress(@RequestBody String dto, HttpServletRequest request, HttpServletResponse response)
			throws AddressException, UserProfileException {
		GeneralResponse gRes = new GeneralResponse();
		Header header = helper.getHeader(dto);
		gRes.setHeader(header);
		logger.info("<------ AddressController : addAddress (BEGIN) ------>");
		try {
			AddressDto req = helper.string2Object(dto, AddressDto.class);
			logger.info("user's tracking id is : {}", header.getTrackingId());
			Address res = service.addAddress(req, request, response);
			gRes.setData(helper.object2String(res));
			gRes.setResponseCode(200);
			logger.info("<------ AddressController : addAddress (END) ------>");
			return ResponseEntity.ok(gRes);
		} catch (Exception e) {
			gRes.setExceptionMsg(e.getMessage());
//			gRes.getExceptions().add(e);
			gRes.setResponseCode(400);
			logger.info("<------ AddressController : addAddress (FAILED) ------>");
			return new ResponseEntity<>(gRes,HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/getAddress")
	public ResponseEntity<GeneralResponse> getAddress(@RequestBody String dto, HttpServletRequest request, HttpServletResponse response)
			throws AddressException, UserProfileException {
		GeneralResponse gRes = new GeneralResponse();
		Header header = helper.getHeader(dto);
		gRes.setHeader(header);
		logger.info("<------ AddressController : addAddress (BEGIN) ------>");
		try {
			CRequest req = helper.string2Object(dto, CRequest.class);
			logger.info("user's tracking id is : {}", header.getTrackingId());
			Address res = service.getAddress(req.getId(), request, response);
			gRes.setData(helper.object2String(res));
			gRes.setResponseCode(200);
			logger.info("<------ AddressController : addAddress (END) ------>");
			return ResponseEntity.ok(gRes);
		} catch (Exception e) {
			gRes.setExceptionMsg(e.getMessage());
//			gRes.getExceptions().add(e);
			gRes.setResponseCode(400);
			logger.info("<------ AddressController : addAddress (FAILED) ------>");
			return new ResponseEntity<>(gRes,HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/getAllAddresses")
	public ResponseEntity<GeneralResponse> getAllAddresses(@RequestBody String dto,HttpServletRequest request, HttpServletResponse response)
			throws AddressException, UserProfileException {
		GeneralResponse gRes = new GeneralResponse();
		Header header = helper.getHeader(dto);
		gRes.setHeader(header);
		logger.info("<------ AddressController : addAddress (BEGIN) ------>");
		try {
			CRequest req = helper.string2Object(dto, CRequest.class);
			logger.info("user's tracking id is : {}", header.getTrackingId());
			List<Address> res = service.getAllAddresses(request, response);
			gRes.setData(helper.object2String(res));
			gRes.setResponseCode(200);
			logger.info("<------ AddressController : addAddress (END) ------>");
			return ResponseEntity.ok(gRes);
		} catch (Exception e) {
			gRes.setExceptionMsg(e.getMessage());
//			gRes.getExceptions().add(e);
			gRes.setResponseCode(400);
			logger.info("<------ AddressController : addAddress (FAILED) ------>");
			return new ResponseEntity<>(gRes,HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/deleteAddress")
	public ResponseEntity<GeneralResponse> deleteAddress(@RequestBody String dto, HttpServletResponse response, HttpServletRequest request)
			throws AddressException, UserProfileException {
		GeneralResponse gRes = new GeneralResponse();
		Header header = helper.getHeader(dto);
		gRes.setHeader(header);
		logger.info("<------ AddressController : addAddress (BEGIN) ------>");
		try {
			CRequest req = helper.string2Object(dto, CRequest.class);
			logger.info("user's tracking id is : {}", header.getTrackingId());
			String res = service.deleteAddress(req.getId(), request, response);
			gRes.setData(helper.object2String(res));
			gRes.setResponseCode(200);
			logger.info("<------ AddressController : addAddress (END) ------>");
			return ResponseEntity.ok(gRes);
		} catch (Exception e) {
			gRes.setExceptionMsg(e.getMessage());
//			gRes.getExceptions().add(e);
			gRes.setResponseCode(400);
			logger.info("<------ AddressController : addAddress (FAILED) with message = {}------>",e.getMessage());
			return new ResponseEntity<>(gRes,HttpStatus.BAD_REQUEST);
		}
	}

}
