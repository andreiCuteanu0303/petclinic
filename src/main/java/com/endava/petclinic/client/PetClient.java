package com.endava.petclinic.client;

import com.endava.petclinic.filters.AuthenticationFilter;
import com.endava.petclinic.filters.LogFilter;
import com.endava.petclinic.model.Pet;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static com.endava.petclinic.util.EnvReader.*;
import static io.restassured.RestAssured.given;

public class PetClient extends BaseClient{

    public Response createPet (Pet pet){

        return getBasicRestConfig()
                .contentType(ContentType.JSON)
                .body(pet)
                .post("/api/pets");
    }
}
