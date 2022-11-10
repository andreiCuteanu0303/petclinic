package com.endava.petclinic.fixture;

import com.endava.petclinic.client.OwnerClient;
import com.endava.petclinic.client.PetClient;
import com.endava.petclinic.model.Owner;
import com.endava.petclinic.testData.TestDataProvider;
import io.restassured.response.Response;
import lombok.Getter;
import org.apache.http.HttpStatus;

public class PetclinicFixture {

    private OwnerClient ownerClient = new OwnerClient();
    private PetClient petClient = new PetClient();

    private TestDataProvider dataProvider = new TestDataProvider();

    @Getter
    private Owner owner;

    public PetclinicFixture createOwner(){
        // creez owner pt a-i asigna un pet
        owner = dataProvider.getOwner();
        // apelez api de addOwner si creez ownerul in baza de date
        Response response = ownerClient.createOwner(owner);
        // validez crearea ownerului
        response.then().statusCode(HttpStatus.SC_CREATED);

        // iau id-ul din response
        long id = response.body().jsonPath().getLong("id");
        // setez id-ul luat pe owner
        owner.setId(id);

        return this;
    }



}
