package com.example.services.service;

import java.util.List;
import feign.Feign; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.cloud.openfeign.FeignClient; 
import com.example.services.model.ServiceEntity;
import com.example.services.repositories.ServiceRepository;

@Service
public class ServiceService {
    
    private final ServiceRepository serviceRepository;

    public List<ServiceEntity> rechercherServices(String recherche) {
        return serviceRepository.findByTitreContainingIgnoreCase(recherche);
    }

    public ServiceService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

  
}
