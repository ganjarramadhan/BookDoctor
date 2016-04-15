package com.ganjarramadhan.bookdoctor.module.history.model;

import com.ganjarramadhan.bookdoctor.module.history.presenter.listener.OnCancelBookingListener;
import com.ganjarramadhan.bookdoctor.module.history.presenter.listener.OnLoadHistoryFinishedListener;
import com.ganjarramadhan.bookdoctor.pojo.Booking;
import com.ganjarramadhan.bookdoctor.pojo.User;

/**
 * Created by ganjarramadhan on 4/11/16.
 */
public interface HistoryModelInterface {

    void loadHistoryData(User user, OnLoadHistoryFinishedListener listener);
    void cancelBooking(Booking booking, OnCancelBookingListener listener);

}
