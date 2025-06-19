package com.homeservices.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.homeservices.model.Address;

public interface AddressRepo extends JpaRepository<Address, Long>{

}
