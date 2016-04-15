package com.ganjarramadhan.bookdoctor.module.history.rest;

import com.ganjarramadhan.bookdoctor.pojo.Booking;
import com.ganjarramadhan.bookdoctor.pojo.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by ganjarramadhan on 4/9/16.
 */
public interface HistoryRestInterface {

    @GET(HistoryAPIURLConstant.PATIENT_HISTORY_URL)
    Call<List<Booking>> getBookingHistory(@Query("patientID") int patientId);

    @FormUrlEncoded
    @POST(HistoryAPIURLConstant.CANCEL_BOOKING_URL)
    Call<Booking> cancelBooking(@Field("bookID") int bookingId);

}
