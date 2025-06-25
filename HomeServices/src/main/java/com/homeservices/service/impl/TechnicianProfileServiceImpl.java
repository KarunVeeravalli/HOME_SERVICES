package com.homeservices.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.homeservices.dto.request.TechnicianProfileDto;
import com.homeservices.enums.URole;
import com.homeservices.exception.ServiceCategoryException;
import com.homeservices.exception.TechnicianProfileException;
import com.homeservices.exception.UserLoginProfileException;
import com.homeservices.exception.UserProfileException;
import com.homeservices.model.Role;
import com.homeservices.model.ServiceCategory;
import com.homeservices.model.TechnicianProfile;
import com.homeservices.model.UserLoginProfile;
import com.homeservices.model.UserProfile;
import com.homeservices.repo.RoleRepo;
import com.homeservices.repo.TechnicianProfileRepo;
import com.homeservices.repo.UserLoginProfileRepo;
import com.homeservices.repo.UserProfileRepo;
import com.homeservices.service.ServiceCategoryService;
import com.homeservices.service.TechnicianProfileService;
import com.homeservices.util.RepoHelper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class TechnicianProfileServiceImpl implements TechnicianProfileService {

    @Autowired
    private RepoHelper helper;

    @Autowired
    private TechnicianProfileRepo repo;

    @Autowired
    private UserLoginProfileRepo loginProfileRepo;

    @Autowired
    private UserProfileRepo userProfileRepo;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private ServiceCategoryService categoryService;

    public static final Logger logger = LogManager.getFormatterLogger(TechnicianProfileServiceImpl.class);

    @Override
    public TechnicianProfile addTechnician(TechnicianProfileDto dto, HttpServletRequest request,
                                           HttpServletResponse response)
            throws TechnicianProfileException, UserProfileException, ServiceCategoryException, UserLoginProfileException {
        logger.info("<------ TechnicianProfileServiceImpl : addTechnician (BEGIN) with request => {} ------>", dto);
        if (helper.IsSuperAdminOrAdmin(request)) {
            if ((!helper.isUserExistsByEmail(dto.getEmail())) || (!helper.isUserExistsByUsername(dto.getUsername()))) {
                UserLoginProfile profile = new UserLoginProfile();
                profile.setEmail(dto.getEmail());
                profile.setMobileNumber(dto.getMobileNumber());
                profile.setUsername(dto.getUsername());
                profile.setPassword(encoder.encode(dto.getUsername() + "@hs.com"));

                Role role = roleRepo.findByName(URole.ROLE_USER);
                Role role2 = roleRepo.findByName(URole.ROLE_TECHNICIAN);
                Set<Role> roles = new HashSet<>();
                roles.add(role);
                roles.add(role2);
                profile.setRoles(roles);
                loginProfileRepo.save(profile);
                logger.info("LoginProfile created for technician successfully with tracking id => {}", dto.getRequestHeader().getTrackingId());

                UserProfile profile1 = new UserProfile();
                profile1.setEmail(dto.getEmail());
                profile1.setMobileNumber(dto.getMobileNumber());
                profile1.setUsername(dto.getUsername());
                profile.setRoles(roles);
                UserProfile us = userProfileRepo.save(profile1);
                logger.info("UserProfile created for technician successfully with tracking id => {}", dto.getRequestHeader().getTrackingId());

                TechnicianProfile techProfile = new TechnicianProfile();
                techProfile.setExperience(dto.getExperience());
                techProfile.setIsAvailable(dto.getIsAvailable());
                techProfile.setUserProfile(us);

                List<ServiceCategory> scs = new ArrayList<>();
                for (Long id : dto.getExpertise()) {
                    ServiceCategory cat = categoryService.getServiceCategory(id, request, response);
                    scs.add(cat);
                }
                techProfile.setExpertise(scs);
                logger.info("TechnicianProfile created and ready to persist with tracking id => {}", dto.getRequestHeader().getTrackingId());
                logger.info("<------ TechnicianProfileServiceImpl : addTechnician (END) ------>");
                return repo.save(techProfile);
            } else {
                logger.warn("User already exists, skipping creation for tracking id => {}", dto.getRequestHeader().getTrackingId());
                throw new UserProfileException("User already exists please update it");
            }
        } else {
            logger.warn("Unauthorized technician creation attempt by non-admin");
            throw new UserProfileException("Only admin can able to add the Services");
        }
    }

    @Override
    public TechnicianProfile updateTechnician(TechnicianProfileDto dto, HttpServletRequest request,
                                              HttpServletResponse response)
            throws TechnicianProfileException, UserProfileException, ServiceCategoryException {
        logger.info("<------ TechnicianProfileServiceImpl : updateTechnician (BEGIN) with request => {} ------>", dto);
        if (repo.existsById(dto.getId())) {
            TechnicianProfile oldProfile = repo.findById(dto.getId()).get();
            oldProfile.setIsAvailable(dto.getIsAvailable());
            logger.info("TechnicianProfile updated successfully for id => {}", dto.getId());
            logger.info("<------ TechnicianProfileServiceImpl : updateTechnician (END) ------>");
            return repo.save(oldProfile);
        } else {
            logger.warn("Technician not found for update with id => {}", dto.getId());
            throw new TechnicianProfileException("Technician doesn't exist");
        }
    }

    @Override
    public TechnicianProfile getTechnician(Long id, HttpServletRequest request, HttpServletResponse response)
            throws TechnicianProfileException, UserProfileException, ServiceCategoryException {
        logger.info("Fetching TechnicianProfile with id => {}", id);
        return repo.findById(id).orElseThrow(() -> new TechnicianProfileException("Technician not found"));
    }

    @Override
    public List<TechnicianProfile> getAllTechnician(HttpServletRequest request, HttpServletResponse response)
            throws TechnicianProfileException, UserProfileException, ServiceCategoryException {
        logger.info("Fetching all TechnicianProfiles");
        return repo.findAll();
    }

    @Override
    public List<TechnicianProfile> getAllTechnicianAvialable(HttpServletRequest request, HttpServletResponse response)
            throws TechnicianProfileException, UserProfileException, ServiceCategoryException {
        logger.info("Fetching all available TechnicianProfiles");
        return repo.findAllByAvialable();
    }

    @Override
    public String deleteTechnician(Long id, HttpServletRequest request, HttpServletResponse response)
            throws TechnicianProfileException, UserProfileException, ServiceCategoryException {
        logger.info("Attempting to delete TechnicianProfile with id => {}", id);
        if (!repo.existsById(id)) {
            logger.warn("Technician with id => {} not found for deletion", id);
            throw new TechnicianProfileException("Technician not found for deletion");
        }
        repo.deleteById(id);
        logger.info("Successfully deleted TechnicianProfile with id => {}", id);
        return "SUCCESSFULLY DELETED";
    }
}