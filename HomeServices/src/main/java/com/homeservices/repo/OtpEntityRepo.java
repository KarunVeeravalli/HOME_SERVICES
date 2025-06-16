package com.homeservices.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.homeservices.model.OtpEntity;

public interface OtpEntityRepo extends JpaRepository<OtpEntity, Long>{
	
	
	@Query(value = "SELECT * from OTP_ENTITY where EMAIL = :email ORDER BY CREATED_DATE_TIME DESC LIMIT 1",nativeQuery = true)
	OtpEntity getLastOtpByEmail(@Param("email") String email);
	
	@Query(value = "SELECT * from OTP_ENTITY where EMAIL = :email and DESCRIPTION = 'OTP FOR PASSWORD CHANGE' ORDER BY CREATED_DATE_TIME DESC LIMIT 1", nativeQuery = true)
	OtpEntity getotpForPasswordChange(@Param("email") String email);

}
