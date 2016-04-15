package com.ganjarramadhan.bookdoctor.module.booking.rest;

import com.ganjarramadhan.bookdoctor.pojo.Booking;
import com.ganjarramadhan.bookdoctor.pojo.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by ganjarramadhan on 4/9/16.
 */
public interface BookingRestInterface {

    @GET(BookingAPIURLConstant.BOOKING_CHECK_URL)
    Call<List<Integer>> checkUnavailableSchedule(@Query("date") String date, @Query("doctorID") int doctorId);

    @FormUrlEncoded
    @POST(BookingAPIURLConstant.BOOKING_URL)
    Call<Booking> doBooking(@Field("patientID") int patientId, @Field("doctorID") int doctorId,
                            @Field("bookingDate") String bookingDate, @Field("timeIndex") int timeIndex);


}
