package com.endava.petclinic.client;

import com.endava.petclinic.filters.AuthenticationFilter;
import com.endava.petclinic.filters.LogFilter;
import com.endava.petclinic.model.Owner;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static com.endava.petclinic.util.EnvReader.*;
import static io.restassured.RestAssured.given;

public class OwnerClient extends  BaseClient{

    public Response createOwner(Owner owner){
                 //replaced by getBaseRestConfig
//        return given().filters(new AuthenticationFilter(), new LogFilter())
//                .baseUri(getBaseUri())
//                .port(getPort())
//                .basePath(getBasePath())
        return getBasicRestConfig()
                .contentType(ContentType.JSON)
                .body(owner)
              .when()
                .post("/api/owners");
    }

    public Response getOwnerById (Long ownerId){
        return getBasicRestConfig()
                .pathParams("ownerId", ownerId)
              .when()
                .get("/api/owners/{ownerId}");
    }

    public Response getOwnerList (){
        return getBasicRestConfig()
              .get("api/owners");
    }

    public Response deleteOwner (Long ownerId){
        return getBasicRestConfig()
                .pathParams("ownerId", ownerId)
              .when()
                .delete("/api/owners/{ownerId}");
    }

    public Response updateOwnerById (Long ownerId, Owner owner){
        return getBasicRestConfig()
                .pathParams("ownerId", ownerId)
                .body(owner)
                .contentType(ContentType.JSON)
              .put("api/owners/{ownerId}");

    }
}
