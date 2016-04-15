package com.ganjarramadhan.bookdoctor.module.booking.view;

import com.ganjarramadhan.bookdoctor.pojo.*;

import java.util.List;

/**
 * Created by ganjarramadhan on 4/11/16.
 */
public interface BookingInterface {

    void onBookingSuccess(com.ganjarramadhan.bookdoctor.pojo.Booking booking);
    void onBookingFailed(String message);

    void onCheckingScheduleSuccess(List<Integer> listOfUnavailableTime);
    void onCheckingScheduleFailed(String message);

    void onUserNotLoggedIn(String message);

    void populateUI(User user);

}
