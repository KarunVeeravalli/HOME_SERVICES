package com.homeservices.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.homeservices.model.UnAuthUser;

public interface UnAuthUserRepo extends JpaRepository<UnAuthUser, Long>{

	Boolean existsByEmail(String email);
	
	@Query(value = "select * from UN_AUTH_USER where email = :email", nativeQuery = true)
	UnAuthUser findByEmail(String email);

}
