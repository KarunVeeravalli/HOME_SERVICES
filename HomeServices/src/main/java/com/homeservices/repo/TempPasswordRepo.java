package com.homeservices.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.homeservices.model.TempPassword;

import lombok.NonNull;

public interface TempPasswordRepo extends JpaRepository<TempPassword, Long>{

	TempPassword findByEmail(@NonNull String email);

}
