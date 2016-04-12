package com.ganjarramadhan.bookdoctor.module.history.presenter.listener;

import com.ganjarramadhan.bookdoctor.pojo.Booking;

import java.util.List;

/**
 * Created by ganjarramadhan on 4/11/16.
 */
public interface OnLoadHistoryFinishedListener {

    void onLoadHistorySuccess(List<Booking> listBooking);
    void onLoadHistoryFailed(String message);

}
