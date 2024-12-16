package com.example.utilisateurs.controller;

public class AuthResponse {
    private String token;
    private String redirection; // Ajoutez cet attribut

    public AuthResponse() {
    }

    public AuthResponse(String token, String redirection) {
        this.token = token;
        this.redirection = redirection;
    }

    // ... autres méthodes

    public String getRedirection() {
        return redirection;
    }

    public void setRedirection(String redirection) {
        this.redirection = redirection;
    }

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}


