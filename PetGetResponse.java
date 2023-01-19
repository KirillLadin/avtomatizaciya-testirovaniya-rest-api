package ru.tinkoff.qa.petStoreService.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PetGetResponse{
	@JsonProperty("message")
	private String message;

	@JsonProperty("code")
	private int code;

	@JsonProperty("type")
	private String type;

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}
}