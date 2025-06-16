package com.homeservices.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.homeservices.model.UserLoginProfie;

public interface UserLoginProfileRepo extends JpaRepository<UserLoginProfie, Long>{

	UserLoginProfie findByEmail(String username);

	UserLoginProfie findByUsername(String username);

	UserLoginProfie findByMobileNumber(Long number);

	void deleteByEmail(String email);

}
