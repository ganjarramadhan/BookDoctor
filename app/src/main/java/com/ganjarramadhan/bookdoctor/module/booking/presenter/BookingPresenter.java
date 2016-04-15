package com.ganjarramadhan.bookdoctor.module.booking.presenter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.ganjarramadhan.bookdoctor.R;
import com.ganjarramadhan.bookdoctor.module.booking.model.BookingModel;
import com.ganjarramadhan.bookdoctor.module.booking.presenter.listener.OnBookingFinishedListener;
import com.ganjarramadhan.bookdoctor.module.booking.presenter.listener.OnCheckScheduleFinishedListener;
import com.ganjarramadhan.bookdoctor.module.booking.view.Booking;
import com.ganjarramadhan.bookdoctor.module.booking.view.BookingInterface;
import com.ganjarramadhan.bookdoctor.pojo.User;
import com.ganjarramadhan.bookdoctor.util.AppsConstant;
import com.securepreferences.SecurePreferences;

import java.lang.ref.WeakReference;
import java.util.Date;
import java.util.List;

/**
 * Created by ganjarramadhan on 4/11/16.
 */
public class BookingPresenter implements BookingPresenterInterface, OnBookingFinishedListener, OnCheckScheduleFinishedListener {

    private WeakReference<BookingInterface> view;
    private BookingModel model;

    public BookingPresenter(BookingInterface view) {
        this.view = new WeakReference<BookingInterface>(view);
        this.model = new BookingModel();
    }

    @Override
    public void onBookingSuccess(com.ganjarramadhan.bookdoctor.pojo.Booking booking) {
        if (null != view.get()) view.get().onBookingSuccess(booking);
    }

    @Override
    public void onBookingFailed(String message) {
        if (null != view.get()) view.get().onBookingFailed(message);
    }

    @Override
    public void submitBooking(Context context, Date bookingDate, int bookingTimeIndex, User doctor) {
        User user = getLoggedInUser(context);
        if (0 != user.getId()){
            com.ganjarramadhan.bookdoctor.pojo.Booking booking =
                    new com.ganjarramadhan.bookdoctor.pojo.Booking(0, user, doctor, bookingDate, bookingTimeIndex);
            model.submitBooking(booking, this);
        } else {
            if (null != view.get()) view.get().onUserNotLoggedIn(context.getString(R.string.not_login_message));
        }
    }

    @Override
    public void checkSchedule(Date date, User doctor) {
        model.checkSchedule(date, doctor, this);
    }

    @Override
    public User getLoggedInUser(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(context.getResources().getString(R.string.app_name), Context.MODE_PRIVATE);
        User user = new User();
        user.setId(prefs.getInt(AppsConstant.KEY_USER_ID, 0));
        user.setEmail(prefs.getString(AppsConstant.KEY_EMAIL, ""));
        user.setType(prefs.getInt(AppsConstant.KEY_USER_TYPE, 0));
        return user;
    }

    @Override
    public void onCheckScheduleSuccess(List<Integer> listUnAvailableDayIndex) {
        if (null != view.get()) view.get().onCheckingScheduleSuccess(listUnAvailableDayIndex);
    }

    @Override
    public void onCheckScheduleFailed(String message) {
        if (null != view.get()) view.get().onCheckingScheduleFailed(message);
    }
}
