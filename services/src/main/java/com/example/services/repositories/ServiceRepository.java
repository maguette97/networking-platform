package com.example.services.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.services.model.ServiceEntity;



public interface ServiceRepository extends JpaRepository<ServiceEntity, Long> {
    List<ServiceEntity> findByValideTrue();

	
	List<ServiceEntity> findByTitreContainingIgnoreCase(String titre);
	
}