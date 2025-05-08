package com.restfulbooker.tests.crud;

import com.restfulbooker.base.BaseTest;
import com.restfulbooker.endpoints.APIConstants;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import org.testng.ITestContext;
import org.testng.annotations.Test;

public class TestDeleteBooking extends BaseTest {

    @Test(groups = "qa",priority = 1)
    @Description("TC#INT4-Verify that booking can be deleted by ID")
    public void testDeleteBookingByID(ITestContext iTestContext){
        //        Token is created by a method in basetest and the same is set as itestcontext attribute
        iTestContext.setAttribute("token",getToken());

//        Here bookingId is also Created by a method in basetest and same is used
        Integer bookingID = createBookingId();

        requestSpecification.
                basePath(APIConstants.CREATE_UPDATE_BOOKING_URL+"/"+bookingID)
                .cookie("token",iTestContext.getAttribute("token"));

        response = RestAssured.given(requestSpecification).when().delete();

        validatableResponse = response.then().statusCode(201).log().all();

    }

}
