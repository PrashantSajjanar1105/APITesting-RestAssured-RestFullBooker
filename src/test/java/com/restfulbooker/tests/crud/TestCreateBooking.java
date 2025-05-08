package com.restfulbooker.tests.crud;

import com.restfulbooker.base.BaseTest;
import com.restfulbooker.endpoints.APIConstants;
import com.restfulbooker.pojos.BookingResponse;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class TestCreateBooking extends BaseTest {

    @Test
    @Description("Verify that booking is created with the given data")
    public void createBooking_TC1(){
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL).log().all();

        response = RestAssured.given(requestSpecification)
                .when().body(payloadManager.setPayloadbookingAsString()).post();

        validatableResponse = response.then().statusCode(200).log().all();

        BookingResponse bookingResponse = payloadManager.getbookingResponsetoJavaObject(response.asString());
        assertActions.verifyStringKey(bookingResponse.getBooking().getFirstname(),"Prashant");
        assertActions.verifyIntegerKeyIsNotNull(bookingResponse.getBookingid());

    }

    @Test
    @Description("Verify that if the price is given in negative booking should not happen")
    public void createBooking_Negative_TC1(){

    }
}
