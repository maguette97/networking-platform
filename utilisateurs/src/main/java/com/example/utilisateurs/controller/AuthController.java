package com.example.utilisateurs.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.utilisateurs.model.Utilisateur;
import com.example.utilisateurs.repository.UtilisateurRepository;
import com.example.utilisateurs.security.JwtUtil;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    // Endpoint pour l'inscription
    @PostMapping("/inscription")
    public ResponseEntity<?> inscription(@RequestBody Utilisateur utilisateur) {
        // Enregistrer l'utilisateur dans la base de données
        Utilisateur existant = utilisateurRepository.findByEmail(utilisateur.getEmail());
        if (existant != null) {
            return ResponseEntity.badRequest().body("Cet email est déjà utilisé.");
        }

        utilisateurRepository.save(utilisateur);
        return ResponseEntity.ok("Inscription réussie !");
    }

 // Endpoint pour la connexion
    @PostMapping("/connexion")
    public ResponseEntity<?> connexion(@RequestBody AuthRequest authRequest) {
        // Vérifier les informations d'authentification
        Utilisateur utilisateur = utilisateurRepository.findByEmail(authRequest.getEmail());
        if (utilisateur == null || !utilisateur.getMotDePasse().equals(authRequest.getMotDePasse())) {
            return ResponseEntity.status(401).body("Email ou mot de passe incorrect.");
        }

        // Créer et retourner un token JWT en cas de succès
        String token = JwtUtil.generateToken(utilisateur);

        // Créer une instance de AuthResponse avec le token et la redirection
        AuthResponse response = new AuthResponse();
        response.setToken(token);
        response.setRedirection("/dashboard"); // Remplacez '/profile' par le chemin de redirection souhaité

        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/dashboard")
    public ResponseEntity<?> getProfile(@RequestHeader("Authorization") String authorizationHeader) {
        // Vérifier si l'en-tête d'autorisation est présent
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Accès non autorisé.");
        }

        String token = authorizationHeader.substring(7); // Retirer le préfixe "Bearer "
        String email = JwtUtil.extractEmail(token); // Implémentez cette méthode pour extraire l'email du token

        Utilisateur utilisateur = utilisateurRepository.findByEmail(email);
        if (utilisateur == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Utilisateur introuvable.");
        }

        // Retourner les informations de profil de l'utilisateur
        return ResponseEntity.ok(utilisateur);
    }


    @PutMapping("/{utilisateurId}/profile")
    public ResponseEntity<?> updateProfile(@PathVariable Long utilisateurId, @RequestBody Utilisateur updatedUtilisateur) {
        // Vérifier si l'utilisateur existe
        Optional<Utilisateur> optionalUtilisateur = utilisateurRepository.findById(utilisateurId);
        if (!optionalUtilisateur.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        // Mettre à jour les champs du profil
        Utilisateur utilisateur = optionalUtilisateur.get();
        utilisateur.setNom(updatedUtilisateur.getNom());
        utilisateur.setEmail(updatedUtilisateur.getEmail());
        utilisateur.setMotDePasse(updatedUtilisateur.getMotDePasse());
        // ... autres champs du profil

        utilisateurRepository.save(utilisateur);
        return ResponseEntity.ok("Profil mis à jour avec succès !");
    }
    
    
    
    
}
