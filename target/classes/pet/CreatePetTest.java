package com.endava.petclinic.pet;

import com.endava.petclinic.TestBaseClass;
import com.endava.petclinic.model.Owner;
import com.endava.petclinic.model.Pet;
import com.endava.petclinic.model.PetType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

public class CreatePetTest extends TestBaseClass {

    @Test
    public void shouldCreatePet(){
        //GIVEN
             // The following 5 lines are going to be replaced by fixture.createOwner().getOwner(); because I've created PetclinicFixture;
//        Owner owner = testDataProvider.getOwner();
//        Response createOwnerResponse = ownerClient.createOwner(owner);
//        createOwnerResponse.then().statusCode(HttpStatus.SC_CREATED);
//        long id = createOwnerResponse.body().jsonPath().getLong("id");
//        owner.setId(id);
        Owner owner = fixture.createOwner().getOwner();

        PetType petType = new PetType();
        petType.setId(1L);
        Pet pet = testDataProvider.getPet(owner, petType);

        //WHEN
        Response response = petClient.createPet(pet);

        //THEN
        response.then().statusCode(HttpStatus.SC_CREATED);
    }
}
