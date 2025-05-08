package com.restfulbooker.tests.crud;

import com.restfulbooker.base.BaseTest;
import com.restfulbooker.endpoints.APIConstants;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class TestHealthCheck extends BaseTest {

    @Test(groups = "qa", priority = 1)
    @Description("")
    public void testHealthCheck(){
        requestSpecification.basePath(APIConstants.PING_URL);

        response = RestAssured.given(requestSpecification).when().get();

        validatableResponse = response.then().statusCode(201).log().all();
    }
}
