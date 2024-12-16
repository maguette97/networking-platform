package com.example.utilisateurs.repository;

import com.example.utilisateurs.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceRepository extends JpaRepository<Service, Long> {
    List<Service> findByValideTrue();
    List<Service> findByValide(boolean valide);
	List<Service> findByValideFalse();
}
