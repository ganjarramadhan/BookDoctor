package com.ganjarramadhan.bookdoctor.module.booking.model;

import com.ganjarramadhan.bookdoctor.module.booking.presenter.listener.OnBookingFinishedListener;
import com.ganjarramadhan.bookdoctor.module.booking.presenter.listener.OnCheckScheduleFinishedListener;
import com.ganjarramadhan.bookdoctor.pojo.Booking;
import com.ganjarramadhan.bookdoctor.pojo.User;

import java.util.Date;

/**
 * Created by ganjarramadhan on 4/11/16.
 */
public interface BookingModelInterface {

    void submitBooking(Booking booking, OnBookingFinishedListener listener);
    void checkSchedule(Date bookingDate, User doctor, OnCheckScheduleFinishedListener listener);

}
