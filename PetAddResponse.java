package ru.tinkoff.qa.petStoreService.models;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PetAddResponse{

	@JsonProperty("name")
	private String name;

	@JsonProperty("id")
	private Object id;

	@JsonProperty("tags")
	private List<TagsItem> tags;

	@JsonProperty("photoUrls")
	private List<String> photoUrls;

	@JsonProperty("category")
	private List<String> category;

	public String getName(){
		return name;
	}

	public Object getId(){
		return id;
	}
}