package ru.tinkoff.qa.petStoreService.models;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PetAdd{

	@JsonProperty("photoUrls")
	private List<String> photoUrls;

	@JsonProperty("name")
	private String name;

	@JsonProperty("id")
	private int id;

	@JsonProperty("message")
	private String message;
	public void setPhotoUrls(List<String> photoUrls){
		this.photoUrls = photoUrls;
	}

	public List<String> getPhotoUrls(){
		return photoUrls;
	}
	public void setId(int id){
		this.id = id;
	}
	public int getId(){
		return id;
	}
	public void setName(String name){
		this.name = name;
	}
	public void setMessage(String message){
		this.message = message;
	}

	public String getName(){
		return name;
	}
	public String getMessage(){
		return message;
	}
}