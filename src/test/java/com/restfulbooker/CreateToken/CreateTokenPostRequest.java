package com.restfulbooker.CreateToken;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class CreateTokenPostRequest {

    RequestSpecification r;
    Response response;
    ValidatableResponse vr;

    @Test
    @Description("Verify that the post request for creating a token gives statuscode 200")
    public void verifyStatusCode_TC1(){

        String payLoad = "{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";
        r = RestAssured.given()
                .baseUri("https://restful-booker.herokuapp.com")
                .basePath("/auth")
                .contentType(ContentType.JSON)
                .body(payLoad);

        response = r.when()
                .log()
                .all()
                .post();

        vr = response.then()
                .log()
                .all()
                .statusCode(200);

    }
}

