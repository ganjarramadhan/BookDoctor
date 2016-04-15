package com.ganjarramadhan.bookdoctor.module.booking.presenter;

import android.content.Context;

import com.ganjarramadhan.bookdoctor.pojo.User;

import java.util.Date;

/**
 * Created by ganjarramadhan on 4/11/16.
 */
public interface BookingPresenterInterface {

    void submitBooking(Context context, Date bookingDate, int bookingTimeIndex, User doctor);
    void checkSchedule(Date date, User doctor);
    User getLoggedInUser(Context context);
}
