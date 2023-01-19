package ru.tinkoff.qa.petStoreService.models;

import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface PetService {

    @RequestLine("POST /pet")
    @Headers("Content-Type:application/json")
    void createPet(PetAdd pet);

    @RequestLine("DELETE /pet/{petId}")
    @Headers("Content-Type:application/json")
    void deletePet (@Param("petId") int id);

    @RequestLine("GET /pet/{petId}")
    @Headers("Content-Type:application/json")
    PetAdd getPet (@Param("petId") int id);
}
