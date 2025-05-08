package com.restfulbooker.tests.integration;

import com.restfulbooker.base.BaseTest;
import com.restfulbooker.endpoints.APIConstants;
import com.restfulbooker.pojos.BookingResponse;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import org.testng.ITestContext;
import org.testng.annotations.Test;

public class TestE2EFlow_02 extends BaseTest {

    // Create Booking -> Delete it -> Verify

    @Test(groups = "qa", priority = 1)
    @Description()
    public void testCreateBooking(ITestContext iTestContext) {

        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL)
                .body(payloadManager.setPayloadbookingAsString());

        response = RestAssured.given(requestSpecification).when().post();

        validatableResponse = response.then().statusCode(200).log().all();

        BookingResponse bookingResponse = payloadManager.getbookingResponsetoJavaObject(response.asString());
        assertActions.verifyResponseBody(bookingResponse.getBooking().getFirstname(), "Prashant", "Verifying the firstname");
        assertActions.verifyIntegerKeyIsNotNull(bookingResponse.getBookingid());

        iTestContext.setAttribute("bookingId", bookingResponse.getBookingid());
    }

    @Test(groups = "qa", priority = 2)
    @Description("TC#INT4-Verify that booking can be deleted by ID")
    public void testDeleteBookingByID(ITestContext iTestContext) {
        String token = getToken();

        requestSpecification.
                basePath(APIConstants.CREATE_UPDATE_BOOKING_URL + "/" + (Integer) iTestContext.getAttribute("bookingId"))
                .cookie("token", token);

        response = RestAssured.given(requestSpecification).when().delete();

        validatableResponse = response.then().statusCode(201).log().all();

    }


}
