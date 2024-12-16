package com.example.utilisateurs.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.utilisateurs.model.Utilisateur;
import com.example.utilisateurs.repository.UtilisateurRepository;

@RestController
@RequestMapping("/api/profile")
@CrossOrigin(origins = "http://localhost:3000")
public class ProfileController {
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @GetMapping("/{prestataireId}")
    public ResponseEntity<?> getProfile(@PathVariable Long prestataireId) {
        // Recherchez le prestataire par ID
        Optional<Utilisateur> optionalPrestataire = utilisateurRepository.findById(prestataireId);
        if (!optionalPrestataire.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Utilisateur prestataire = optionalPrestataire.get();
        // Retournez les informations de profil du prestataire
        return ResponseEntity.ok(prestataire);
    }
}
