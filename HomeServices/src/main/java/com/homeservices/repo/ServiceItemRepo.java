package com.homeservices.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.homeservices.model.ServiceItem;

public interface ServiceItemRepo extends JpaRepository<ServiceItem, Long>{

	boolean existsByName(String name);
	
	@Query(value="SELECT * from SERVICE_ITEM where  CATEGORY_ID = :categoryId",nativeQuery = true)
	List<ServiceItem> getAllItemsByCategory(@Param("categoryId") Long categoryId);

}
