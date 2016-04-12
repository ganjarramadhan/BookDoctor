package com.ganjarramadhan.bookdoctor.module.booking.model;

import com.ganjarramadhan.bookdoctor.module.booking.presenter.listener.OnBookingFinishedListener;
import com.ganjarramadhan.bookdoctor.module.booking.view.Booking;

/**
 * Created by ganjarramadhan on 4/11/16.
 */
public interface BookingModelInterface {

    void submitBooking(Booking booking, OnBookingFinishedListener listener);

}
