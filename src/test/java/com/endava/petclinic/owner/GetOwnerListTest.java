package com.endava.petclinic.owner;

import com.endava.petclinic.TestBaseClass;
import com.endava.petclinic.model.Owner;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.withArgs;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

public class GetOwnerListTest extends TestBaseClass {

    @Test
    public void shouldGetOwnerList() {
        //GIVEN
//        Owner owner = new Owner("John", "Cena", "TX", "Texas", "12345678"); > No longer needed thanks to javafaker
        Owner owner = testDataProvider.getOwner();
        Response createOwnerResponse = ownerClient.createOwner(owner);
        createOwnerResponse.then().statusCode(HttpStatus.SC_CREATED);
        Long ownerId = createOwnerResponse.body().jsonPath().getLong("id");

        //WHEN
        Response response = ownerClient.getOwnerList();

        //THEN
        //validate each field
        response.then().statusCode(HttpStatus.SC_OK)
                .body("find{ it -> it.id == %s }.firstName", withArgs(ownerId), is(owner.getFirstName()));
                             // ^ it = each json from the list / %s ^ = ownerId // https://github.com/rest-assured/rest-assured/wiki/Usage#json-example
                             // so in the above we try to validate firstName from the newly created owner based on the id;
        // find entity and validate it
        Object actualOwner = response.body().jsonPath().param("id", ownerId).getObject("find{ it -> it.id == id }", Owner.class);
        assertThat(actualOwner, is(owner));

        // serialize in a List of objects and verify that the list contains what I need
        List<Owner> ownerList = response.body().jsonPath().getList("", Owner.class);
        assertThat(ownerList, hasItem(owner));
    }
}
