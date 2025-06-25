package com.homeservices.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.homeservices.model.UserProfile;

public interface UserProfileRepo extends JpaRepository<UserProfile, Long>{
	
	@Query("select t from UserProfile t where email= :email")
	public UserProfile getUserByEmail(String email);

	public UserProfile findByEmail(String email);

	public void deleteByEmail(String email);

	@Query("select t from UserProfile t where username= :username")
	public UserProfile getUserByUsername(String username);

	@Query("select t from UserProfile t where mobileNumber= :mobileNumber")
	public UserProfile getUserByMobileNumber(String mobileNumber);

}
