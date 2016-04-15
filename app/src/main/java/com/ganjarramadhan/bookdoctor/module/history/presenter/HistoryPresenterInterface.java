package com.ganjarramadhan.bookdoctor.module.history.presenter;

import android.content.Context;

import com.ganjarramadhan.bookdoctor.pojo.Booking;

/**
 * Created by ganjarramadhan on 4/11/16.
 */
public interface HistoryPresenterInterface {

    void loadHistory(Context context);

    void cancelBooking(Context context, Booking booking);

}
