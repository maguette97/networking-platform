package com.example.utilisateurs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.utilisateurs.model.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    Utilisateur findByEmail(String email);
}
