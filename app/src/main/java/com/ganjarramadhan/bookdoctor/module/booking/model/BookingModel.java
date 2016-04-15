package com.ganjarramadhan.bookdoctor.module.booking.model;

import com.ganjarramadhan.bookdoctor.module.booking.presenter.listener.OnBookingFinishedListener;
import com.ganjarramadhan.bookdoctor.module.booking.presenter.listener.OnCheckScheduleFinishedListener;
import com.ganjarramadhan.bookdoctor.module.booking.rest.BookingRestClient;
import com.ganjarramadhan.bookdoctor.pojo.Booking;
import com.ganjarramadhan.bookdoctor.pojo.User;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ganjarramadhan on 4/11/16.
 */
public class BookingModel implements BookingModelInterface {

    @Override
    public void submitBooking(final Booking booking, final OnBookingFinishedListener listener) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Call<Booking> bookingCall = BookingRestClient.get().doBooking(booking.getCustomer().getId(),
                booking.getDoctor().getId(), sdf.format(booking.getBookingDate()), booking.getBookingTime());
        bookingCall.enqueue(new Callback<Booking>() {
            @Override
            public void onResponse(Call<Booking> call, Response<Booking> response) {
                if (response.isSuccessful()) {
                    listener.onBookingSuccess(response.body());
                } else {
                    listener.onBookingFailed(response.message());
                }
            }

            @Override
            public void onFailure(Call<Booking> call, Throwable t) {
                listener.onBookingFailed(t.getMessage());
            }
        });

    }

    @Override
    public void checkSchedule(Date bookingDate, User doctor, final OnCheckScheduleFinishedListener listener) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Call<List<Integer>> checkUnavailableSchedule = BookingRestClient.get()
                .checkUnavailableSchedule(sdf.format(bookingDate), doctor.getId());
        checkUnavailableSchedule.enqueue(new Callback<List<Integer>>() {
            @Override
            public void onResponse(Call<List<Integer>> call, Response<List<Integer>> response) {

                if (response.isSuccessful()){
                    listener.onCheckScheduleSuccess(response.body());
                } else {
                    listener.onCheckScheduleFailed(response.message());
                }

            }

            @Override
            public void onFailure(Call<List<Integer>> call, Throwable t) {
                listener.onCheckScheduleFailed(t.getMessage());
            }
        });

    }
}
