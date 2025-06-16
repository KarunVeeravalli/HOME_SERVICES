package com.homeservices.util;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.homeservices.enums.URole;
import com.homeservices.model.Role;
import com.homeservices.repo.RoleRepo;

import jakarta.transaction.Transactional;

@Component
public class Comp implements CommandLineRunner{
	
	@Autowired
	private RoleRepo repo;
	
	@Override
	@Transactional
	public void run(String... args) throws Exception {
		if (repo.findAll().size() <= 0) {

//			repo.saveAll(Arrays.asList(new Role((long) 1005, URole.ROLE_USER), new Role((long) 1006, URole.ROLE_ADMIN),
//					new Role((long) 1007, URole.ROLE_SUPERADMIN)));
			
			repo.saveAll(Arrays.asList(new Role( URole.ROLE_USER), new Role(URole.ROLE_ADMIN),
					new Role( URole.ROLE_SUPERADMIN)));


			System.out.println("Roles found in the repo are ---> " + (repo.findAll().size() != 0
					? repo.findAll().stream().map(Role::getName).collect(Collectors.toList())
					: "0"));

		}
	}

}
