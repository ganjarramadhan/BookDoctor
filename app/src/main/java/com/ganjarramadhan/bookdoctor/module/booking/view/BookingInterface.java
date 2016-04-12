package com.ganjarramadhan.bookdoctor.module.booking.view;

import com.ganjarramadhan.bookdoctor.pojo.User;

/**
 * Created by ganjarramadhan on 4/11/16.
 */
public interface BookingInterface {

    void onBookingSuccess(Booking booking);
    void onBookingFailed(String message);

    void populateUI(User user);

}
