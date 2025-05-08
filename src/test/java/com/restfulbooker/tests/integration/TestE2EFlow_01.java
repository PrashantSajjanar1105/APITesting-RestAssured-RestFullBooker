package com.restfulbooker.tests.integration;

import com.google.gson.Gson;
import com.restfulbooker.base.BaseTest;
import com.restfulbooker.endpoints.APIConstants;
import com.restfulbooker.pojos.Booking;
import com.restfulbooker.pojos.BookingResponse;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

public class TestE2EFlow_01 extends BaseTest {

    //  Test E2E Scenario 1
    //  1. Create a Booking -> bookingID
    // 2. Create Token -> token
    // 3. Verify that the Create Booking is working - GET Request to bookingID
    // 4. Update the booking ( bookingID, Token) - Need to get the token, bookingID from above request
    // 5. Delete the Booking - Need to get the token, bookingID from above request


    // Create A Booking, Create a Token
    // Verify that Get booking -
    // Update the Booking
    // Delete the Booking

    @Test(groups = "qa" ,priority = 1)
    @Description()
    public void testCreateBooking(ITestContext iTestContext){

        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL)
                .body(payloadManager.setPayloadbookingAsString());

        response = RestAssured.given(requestSpecification).when().post();

        validatableResponse = response.then().statusCode(200).log().all();

        BookingResponse bookingResponse = payloadManager.getbookingResponsetoJavaObject(response.asString());
        assertActions.verifyResponseBody(bookingResponse.getBooking().getFirstname(),"Prashant","Verifying the firstname");
        assertActions.verifyIntegerKeyIsNotNull(bookingResponse.getBookingid());

        iTestContext.setAttribute("bookingId", bookingResponse.getBookingid());
    }

    @Test(groups = "qa",priority = 2)
    @Description("TC#INT2-Verify the booking by id")
    public void testVerifyBookingByID(ITestContext iTestContext){

        requestSpecification.
                basePath(APIConstants.CREATE_UPDATE_BOOKING_URL+"/"+(Integer)iTestContext.getAttribute("bookingId")).log().all();
        response = RestAssured.given(requestSpecification).when().get();

        validatableResponse = response.then().statusCode(200).log().all();

        Booking booking = payloadManager.getBookingToJavaObj(response.asString());
        assertActions.verifyResponseBody(booking.getFirstname(),"Prashant","Verify Firstname");
        assertActions.verifyStringKeyIsNotNull(booking.getFirstname()
        );

    }

    @Test(groups = "qa",priority = 3)
    @Description("TC#INT3-Verify that booking can be updated by ID")
    public void testUpdateBookingByID(ITestContext iTestContext){
//        Token is created using a method in BaseTest
       iTestContext.setAttribute("token",getToken());

        requestSpecification.
                basePath(APIConstants.CREATE_UPDATE_BOOKING_URL+"/"+(Integer)iTestContext.getAttribute("bookingId"))
                .body(payloadManager.setPayloadUpdatebookingAsString())
                .cookie("token",iTestContext.getAttribute("token"));

        response = RestAssured.given(requestSpecification).when().put();

        validatableResponse = response.then().statusCode(200).log().all();

        Booking booking = payloadManager.getBookingToJavaObj(response.asString());
        assertActions.verifyStringKeyIsNotNull(booking.getFirstname());
        assertActions.verifyResponseBody(booking.getFirstname(),"Mahesh","Verify update of the firstname");
        assertActions.verifyResponseBody(booking.getLastname(),"Alur","Verify that the last name is updated");

    }

    @Test(groups = "qa",priority = 4)
    @Description("TC#INT4-Verify that booking can be deleted by ID")
    public void testDeleteBookingByID(ITestContext iTestContext){
        requestSpecification.
                basePath(APIConstants.CREATE_UPDATE_BOOKING_URL+"/"+(Integer)iTestContext.getAttribute("bookingId"))
                .cookie("token",iTestContext.getAttribute("token"));

        response = RestAssured.given(requestSpecification).when().delete();

        validatableResponse = response.then().statusCode(201).log().all();

    }


}
