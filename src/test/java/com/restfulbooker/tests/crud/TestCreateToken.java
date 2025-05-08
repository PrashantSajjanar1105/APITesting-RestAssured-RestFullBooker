package com.restfulbooker.tests.crud;

import com.restfulbooker.base.BaseTest;
import com.restfulbooker.endpoints.APIConstants;
import com.restfulbooker.pojos.TokenResponse;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class TestCreateToken extends BaseTest {

    @Test(groups = "qa" , priority = 1)
    @Description("TC#2 : Verify that the Create Token post request gives the token as a response and gives the statuscode 200")
    public void testCreateToken(){
        requestSpecification.basePath(APIConstants.AUTH_URL).log().all();

        response = RestAssured.given(requestSpecification).body(payloadManager.setAuthPayload())
                .post();

        validatableResponse = response.then().statusCode(200).log().all();

        TokenResponse tokenResponse = payloadManager.getTokenResponseToJavaObj(response.asString());
        assertActions.verifyStringKeyIsNotNull(tokenResponse.getToken());

    }
}

