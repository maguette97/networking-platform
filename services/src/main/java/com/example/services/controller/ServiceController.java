package com.example.services.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.example.services.model.ServiceEntity;
import com.example.services.repositories.ServiceRepository;
import com.example.services.service.ServiceService;
import com.example.utilisateurs.model.Utilisateur;

@RestController
@RequestMapping("/api/services")
@CrossOrigin(origins = "http://localhost:3000")
public class ServiceController {
    @Autowired
    private ServiceService serviceService;
    
    @Autowired
    private ServiceRepository serviceRepository;

    @GetMapping("/search")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<ServiceEntity> searchServicesByTitle(@RequestParam String titre) {
        return serviceRepository.findByTitreContainingIgnoreCase(titre);
    }
    



   
}
