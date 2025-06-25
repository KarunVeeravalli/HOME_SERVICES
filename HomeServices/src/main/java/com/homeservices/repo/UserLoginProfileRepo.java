package com.homeservices.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.homeservices.model.UserLoginProfile;

public interface UserLoginProfileRepo extends JpaRepository<UserLoginProfile, Long>{

	UserLoginProfile findByEmail(String username);

	UserLoginProfile findByUsername(String username);

	UserLoginProfile findByMobileNumber(Long number);

	void deleteByEmail(String email);

}
