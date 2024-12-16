package com.example.utilisateurs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.utilisateurs.model.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
}

