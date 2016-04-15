package com.ganjarramadhan.bookdoctor.module.booking.presenter.listener;


import com.ganjarramadhan.bookdoctor.pojo.Booking;

/**
 * Created by ganjarramadhan on 4/11/16.
 */
public interface OnBookingFinishedListener {

    void onBookingSuccess(Booking booking);
    void onBookingFailed(String message);

}
