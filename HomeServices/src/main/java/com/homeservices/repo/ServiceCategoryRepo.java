package com.homeservices.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.homeservices.model.ServiceCategory;

public interface ServiceCategoryRepo extends JpaRepository<ServiceCategory, Long>{

	boolean existsByName(String name);

}
