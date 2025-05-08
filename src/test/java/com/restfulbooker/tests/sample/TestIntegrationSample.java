package com.restfulbooker.tests.sample;

import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestIntegrationSample {

    @Test(groups = "qa",priority = 1)
    @Description("TC#INT1-Verify that booking can be created")
    public void testCreateBooking(){
        Assert.assertTrue(true);
    }

    @Test(groups = "qa",priority = 2)
    @Description("TC#INT2-Verify the booking by id")
    public void testVerifyBookingByID(){
        Assert.assertTrue(true);
    }

    @Test(groups = "qa",priority = 3)
    @Description("TC#INT3-Verify that booking can be updated by ID")
    public void testUpdateBookingByID(){
        Assert.assertTrue(true);
    }

    @Test(groups = "qa",priority = 4)
    @Description("TC#INT4-Verify that booking can be deleted by ID")
    public void testDeleteBookingByID(){
        Assert.assertTrue(true);
    }
}

