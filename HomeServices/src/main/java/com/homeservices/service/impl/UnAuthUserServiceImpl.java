package com.homeservices.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homeservices.enums.Status;
import com.homeservices.exception.UnAuthUserException;
import com.homeservices.exception.UserProfileException;
import com.homeservices.model.UnAuthUser;
import com.homeservices.repo.UnAuthUserRepo;
import com.homeservices.service.UnAuthUserService;
import com.homeservices.util.RepoHelper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class UnAuthUserServiceImpl implements UnAuthUserService {
	
	@Autowired
	private UnAuthUserRepo repo;
	
	@Autowired
	private RepoHelper helper;
	
	@Override
	public UnAuthUser saveUser(UnAuthUser user, HttpServletRequest request, HttpServletResponse response)
			throws UnAuthUserException, UserProfileException {
		return repo.save(user);
	}

	@Override
	public Status deleteUser(String email, HttpServletRequest request, HttpServletResponse response)
			throws UnAuthUserException, UserProfileException {
			UnAuthUser user = repo.findByEmail(email);
//			if(user==null) {
//				throw new UnAuthUserException("User not found, Please Register first to continue");
//			}
			repo.deleteById(user.getId());
		return Status.SUCCESS;
	}

	@Override
	public UnAuthUser getUser(String email, HttpServletRequest request, HttpServletResponse response)
			throws UnAuthUserException {
		UnAuthUser user = repo.findByEmail(email);
//		if(user==null) {
//			throw new UnAuthUserException("User not found, Please Register first to continue");
//		}
		return user;
	}

	@Override
	public UnAuthUser updateUser(UnAuthUser user, HttpServletRequest request, HttpServletResponse response)
			throws UnAuthUserException {
		UnAuthUser user1 = repo.findByEmail(user.getEmail());
//		if(user1==null) {
//			throw new UnAuthUserException("User not found, Please Register first to continue");
//		}
		BeanUtils.copyProperties(user, user1, helper.getNullPropertyNames(user));
		return repo.save(user1);
	}

}
