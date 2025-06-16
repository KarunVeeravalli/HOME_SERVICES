package com.homeservices.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.homeservices.model.Email;

public interface EmailRepo extends JpaRepository<Email, Long>{

}
