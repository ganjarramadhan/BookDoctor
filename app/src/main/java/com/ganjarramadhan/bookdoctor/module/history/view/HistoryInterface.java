package com.ganjarramadhan.bookdoctor.module.history.view;

import com.ganjarramadhan.bookdoctor.pojo.Booking;

import java.util.List;

/**
 * Created by ganjarramadhan on 4/11/16.
 */
public interface HistoryInterface {

    void onLoadHistorySuccess(List<Booking> listBooking);
    void onLoadHistoryFailed(String message);

    void onCancelBooking(Booking booking);
    void onCancelBookingSuccess(Booking booking);
    void onCancelBookingFailed(String message);

}
