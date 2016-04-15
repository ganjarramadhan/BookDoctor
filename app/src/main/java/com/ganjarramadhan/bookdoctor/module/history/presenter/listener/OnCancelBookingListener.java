package com.ganjarramadhan.bookdoctor.module.history.presenter.listener;

import com.ganjarramadhan.bookdoctor.pojo.Booking;

/**
 * Created by ganjarramadhan on 4/14/16.
 */
public interface OnCancelBookingListener {

    void onCancelSuccess(Booking booking);
    void onCancelFailed(String message);

}
