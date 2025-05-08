package com.restfulbooker.tests.crud;

import com.restfulbooker.base.BaseTest;
import com.restfulbooker.endpoints.APIConstants;
import com.restfulbooker.pojos.Booking;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import org.testng.ITestContext;
import org.testng.annotations.Test;

public class TestUpdateBooking extends BaseTest {

    @Test(groups = "qa",priority = 1)
    @Description("TC#INT3-Verify that booking can be updated by ID")
    public void testUpdateBookingByID(ITestContext iTestContext){

//        Token is created by a method in basetest and the same is set as itestcontext attribute
        iTestContext.setAttribute("token",getToken());

//        Here bookingId is also Created by a method in basetest and same is used
        Integer bookingID = createBookingId();

        requestSpecification.
                basePath(APIConstants.CREATE_UPDATE_BOOKING_URL+"/"+bookingID)
                .body(payloadManager.setPayloadUpdatebookingAsString())
                .cookie("token",iTestContext.getAttribute("token"));

        response = RestAssured.given(requestSpecification).when().put();

        validatableResponse = response.then().statusCode(200).log().all();

        Booking booking = payloadManager.getBookingToJavaObj(response.asString());
        assertActions.verifyStringKeyIsNotNull(booking.getFirstname());
        assertActions.verifyResponseBody(booking.getFirstname(),"Mahesh","Verify update of the firstname");
        assertActions.verifyResponseBody(booking.getLastname(),"Alur","Verify that the last name is updated");

    }
}
