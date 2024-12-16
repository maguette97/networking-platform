package com.example.utilisateurs.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.example.utilisateurs.model.Message;

@Controller
public class WebSocketController {
    
    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/publicChatRoom")
    public Message sendMessage(@Payload Message message) {
        // Enregistrez le message dans la base de données
        // Envoyez le message à tous les clients abonnés
        return message;
    }
}
