package com.endava.petclinic.owner;

import com.endava.petclinic.TestBaseClass;
import com.endava.petclinic.model.Owner;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

public class DeleteOwnerTest extends TestBaseClass {

    @Test
    public void shouldRemoveOwnerGivenAnId () {
        //GIVEN
//        Owner owner = new Owner("John", "Cena", "TX", "Texas", "12345678"); > No longer needed thanks to javafaker
        Owner owner = testDataProvider.getOwner();
        Response createOwnerResponse = ownerClient.createOwner(owner);
        createOwnerResponse.then().statusCode(HttpStatus.SC_CREATED);
        Long ownerId = createOwnerResponse.body().jsonPath().getLong("id");

        //WHEN
        Response response = ownerClient.deleteOwner(ownerId);

        //THEN
        response.then().statusCode(HttpStatus.SC_NO_CONTENT);
    }
}
