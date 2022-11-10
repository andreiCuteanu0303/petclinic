package com.endava.petclinic.owner;

import com.endava.petclinic.TestBaseClass;
import com.endava.petclinic.client.OwnerClient;
import com.endava.petclinic.model.Owner;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class CreateOwnerTest extends TestBaseClass {

    @Test
    public void shouldCreateOwner(){
        //GIVEN
//        Owner owner = new Owner("Steve", "Rogers", "London Av.", "London", "404123123"); > No longer needed thanks to javafaker
        Owner owner = testDataProvider.getOwner();

        //WHEN
        Response response = ownerClient.createOwner(owner);

        //THEN
        response.then().statusCode( HttpStatus.SC_CREATED )
                .body("id", is(notNullValue()));

        long id = response.body().jsonPath().getLong("id");
        Owner actualOwnerInDB = db.getOwnerById(id);
        assertThat(actualOwnerInDB, is(owner));
    }

    @Test
    public void shouldFailToCreateOwnerGivenEmptyFirstName(){
//        Owner owner = new Owner("", "Rogers", "London Av.", "London", "404123123"); > No longer needed thanks to javafaker
        Owner owner = testDataProvider.getOwner();
        owner.setFirstName("");

        //WHEN
        Response response = ownerClient.createOwner(owner);

        //THEN
        response.then().statusCode( HttpStatus.SC_BAD_REQUEST );
    }

    @Test
    public void shouldFailToCreateOwnerGivenFewDigitsTelephone(){
        Owner owner = testDataProvider.getOwner();
        owner.setTelephone(testDataProvider.getNumberWithDigits(0, 6));

        //WHEN
        Response response = ownerClient.createOwner(owner);

        //THEN
        response.then().statusCode( HttpStatus.SC_BAD_REQUEST );
    }

    @Test
    public void shouldFailToCreateOwnerGivenTooManyDigitsTelephone(){
        Owner owner = testDataProvider.getOwner();
        owner.setTelephone(testDataProvider.getNumberWithDigits(11, 100));

        //WHEN
        Response response = ownerClient.createOwner(owner);

        //THEN
        response.then().statusCode( HttpStatus.SC_BAD_REQUEST );
    }
}
