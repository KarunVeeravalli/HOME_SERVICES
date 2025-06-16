package com.homeservices.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.homeservices.model.UserProfile;

public interface UserProfileRepo extends JpaRepository<UserProfile, Long>{
	
	@Query("select t from UserProfile t where email= :email")
	public UserProfile getUserByEmail(String email);

	public UserProfile findByEmail(String email);

	public void deleteByEmail(String email);

}
