package com.homeservices.security.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.homeservices.model.UserLoginProfie;
import com.homeservices.repo.UserLoginProfileRepo;

@Service
public class UserDetailsImplService implements UserDetailsService{
	
	@Autowired
	private UserLoginProfileRepo loginProfileRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserLoginProfie user = new UserLoginProfie();
		if(username.contains("@gmail.com")) {
			user = loginProfileRepo.findByEmail(username);
		}else {
			user = loginProfileRepo.findByUsername(username);
		}
		
		if(user==null) {
			throw new UsernameNotFoundException("User not found with the input ----> "+username);
		}
		return UserDetailsImpl.build(user);
	}

	
}
