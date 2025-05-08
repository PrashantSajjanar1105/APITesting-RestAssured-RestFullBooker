package com.restfulbooker.modules;

import com.google.gson.Gson;
import com.restfulbooker.pojos.*;

public class PayloadManager {

    Gson gson;

//    Converting Java Object to Json as String
    public String setPayloadbookingAsString(){
        Booking booking = new Booking();
        booking.setFirstname("Prashant");
        booking.setLastname("Sajjanar");
        booking.setTotalprice(114);
        booking.setDepositpaid(true);

        BookingDates bookingdates = new BookingDates();
        bookingdates.setCheckin("2025-05-04");
        bookingdates.setCheckout("2025-05-04");

        booking.setBookingdates(bookingdates);
        booking.setAdditionalneeds("Breakfast");

        return new Gson().toJson(booking);
    }

//    Converting JSON response to Java Object
    public BookingResponse getbookingResponsetoJavaObject(String responseString){
        return new Gson().fromJson(responseString, BookingResponse.class);
    }

//    Setting Auth Payload
    public String setAuthPayload(){
        Auth auth = new Auth();
        auth.setUsername("admin");
        auth.setPassword("password123");
        return new Gson().toJson(auth);
    }

//    converting token response to java object
    public TokenResponse getTokenResponseToJavaObj(String tokenResponse){
        return new Gson().fromJson(tokenResponse, TokenResponse.class);
    }

//    converting Booking type response to  java object
    public Booking getBookingToJavaObj(String response){
        return new Gson().fromJson(response, Booking.class);
    }

    public String setPayloadUpdatebookingAsString(){
        Booking booking = new Booking();
        booking.setFirstname("Mahesh");
        booking.setLastname("Alur");
        booking.setTotalprice(112);
        booking.setDepositpaid(false);

        BookingDates bookingdates = new BookingDates();
        bookingdates.setCheckin("2025-05-04");
        bookingdates.setCheckout("2025-05-04");

        booking.setBookingdates(bookingdates);
        booking.setAdditionalneeds("Breakfast");

        return new Gson().toJson(booking);
    }

}
