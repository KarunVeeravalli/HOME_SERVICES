package com.homeservices.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.homeservices.enums.URole;
import com.homeservices.model.Role;

public interface RoleRepo extends JpaRepository<Role, Long>{

	Role findByName(URole roleSuperadmin);

}
