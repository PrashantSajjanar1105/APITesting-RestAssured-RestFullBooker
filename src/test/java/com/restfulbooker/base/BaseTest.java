package com.restfulbooker.base;

import com.restfulbooker.assets.AssertActions;
import com.restfulbooker.endpoints.APIConstants;
import com.restfulbooker.modules.PayloadManager;
import com.restfulbooker.pojos.BookingResponse;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.testng.annotations.BeforeTest;

public class BaseTest {
    public RequestSpecification requestSpecification;
    public PayloadManager payloadManager;
    public AssertActions assertActions;
    public JsonPath jsonPath;
    public Response response;
    public ValidatableResponse validatableResponse;

    @BeforeTest
    public void setUp(){
        payloadManager = new PayloadManager();
        assertActions = new AssertActions();
        requestSpecification = RestAssured.given()
                .baseUri(APIConstants.BASE_URI)
                .contentType(ContentType.JSON).log().all();
    }

    public String getToken(){
        setUp();
        requestSpecification.basePath(APIConstants.AUTH_URL)
                .body(payloadManager.setAuthPayload());

        response = RestAssured.given(requestSpecification).when().post();
        System.out.println(payloadManager.getTokenResponseToJavaObj(response.asString()).getToken());
        return payloadManager.getTokenResponseToJavaObj(response.asString()).getToken();
    }

    public Integer createBookingId(){
        setUp();
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL)
                .body(payloadManager.setPayloadbookingAsString());

        response = RestAssured.given(requestSpecification).when().post();

        BookingResponse bookingResponse = payloadManager.getbookingResponsetoJavaObject(response.asString());

        System.out.println(bookingResponse.getBookingid());

        return bookingResponse.getBookingid();
    }

}
