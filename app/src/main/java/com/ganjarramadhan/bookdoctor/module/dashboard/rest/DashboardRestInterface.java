package com.ganjarramadhan.bookdoctor.module.dashboard.rest;

import com.ganjarramadhan.bookdoctor.pojo.Booking;
import com.ganjarramadhan.bookdoctor.pojo.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ganjarramadhan on 4/9/16.
 */
public interface DashboardRestInterface {

    @GET(DashboardAPIURLConstant.GET_USERS_URL)
    Call<List<User>> getUsers(@Query("group_id") int groupId, @Query("active") int isActive);

    @GET(DashboardAPIURLConstant.GET_DOCTOR_HISTORY_URL)
    Call<List<Booking>> getDoctorsHistory(@Query("doctorID") int doctorId);
}
