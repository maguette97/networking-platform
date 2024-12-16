package com.example.services.dto;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.utilisateurs.model.Utilisateur;

@FeignClient(name = "utilisateurs", url = "http://localhost:8080") // URL du microservice utilisateurs
public interface UserServiceClient {

    @GetMapping("/api/utilisateurs/{userId}")
    Utilisateur getUserById(@PathVariable("userId") Long userId);

    // Ajoutez d'autres méthodes de communication avec le microservice utilisateurs ici
}
