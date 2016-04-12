package com.ganjarramadhan.bookdoctor.module.booking.presenter;

import com.ganjarramadhan.bookdoctor.module.booking.model.BookingModel;
import com.ganjarramadhan.bookdoctor.module.booking.presenter.listener.OnBookingFinishedListener;
import com.ganjarramadhan.bookdoctor.module.booking.view.Booking;
import com.ganjarramadhan.bookdoctor.module.booking.view.BookingInterface;

import java.lang.ref.WeakReference;

/**
 * Created by ganjarramadhan on 4/11/16.
 */
public class BookingPresenter implements BookingPresenterInterface, OnBookingFinishedListener {

    private WeakReference<BookingInterface> view;
    private BookingModel model;

    public BookingPresenter(BookingInterface view) {
        this.view = new WeakReference<BookingInterface>(view);
        this.model = new BookingModel();
    }

    @Override
    public void onBookingSuccess(Booking booking) {
        if (null != view.get()) view.get().onBookingSuccess(booking);
    }

    @Override
    public void onBookingFailed(String message) {
        if (null != view.get()) view.get().onBookingFailed(message);
    }

    @Override
    public void submitBooking(Booking booking) {
        model.submitBooking(booking, this);
    }
}
