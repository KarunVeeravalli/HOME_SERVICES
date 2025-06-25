package com.homeservices.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.homeservices.model.TechnicianProfile;

public interface TechnicianProfileRepo extends JpaRepository<TechnicianProfile, Long>{
	
	@Query(value = "SELECT * FROM TECHNICIAN_PROFILE WHERE IS_AVAILABLE = true",nativeQuery = true)
	//for sql db 
//	@Query(value = "SELECT * FROM TECHNICIAN_PROFILE WHERE IS_AVAILABLE = 1",nativeQuery = true)
	List<TechnicianProfile> findAllByAvialable();
	

	


}
