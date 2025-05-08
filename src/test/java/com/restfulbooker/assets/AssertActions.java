package com.restfulbooker.assets;

import io.restassured.response.Response;
import org.testng.Assert;
import org.assertj.core.api.Assertions.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AssertActions {

//    TestNG assertions
    public void verifyResponseBody(String actual, String expected, String description){
       Assert.assertEquals(actual,expected,description);
    }

    public void verifyResponseBody(int actual, int expected, String description){
        Assert.assertEquals(actual,expected,description);
    }

    public void verifyStatusCode(Response response, Integer expected){
        Assert.assertEquals(response.getStatusCode(),expected);
    }

//    AssertJ assertion

    public void verifyStringKey(String keyActual, String keyExpected){
        assertThat(keyActual).isNotNull().isNotBlank().isEqualTo(keyExpected);
    }

    public void verifyIntegerKeyIsNotNull(Integer keyActual){
        assertThat(keyActual).isNotNull();
    }

    public void verifyStringKeyIsNotNull(String keyActual){
        assertThat(keyActual).isNotNull();
    }





}
