package com.example.services.dto;

public class DemandeDto {
	private Long prestataireId;
	private Long clientId;
	private String message;

	// Getters and setters

	public Long getPrestataireId() {
		return prestataireId;
	}

	public void setPrestataireId(Long prestataireId) {
		this.prestataireId = prestataireId;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
