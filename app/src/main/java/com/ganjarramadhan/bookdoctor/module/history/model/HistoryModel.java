package com.ganjarramadhan.bookdoctor.module.history.model;

import com.ganjarramadhan.bookdoctor.module.history.presenter.listener.OnCancelBookingListener;
import com.ganjarramadhan.bookdoctor.module.history.presenter.listener.OnLoadHistoryFinishedListener;
import com.ganjarramadhan.bookdoctor.module.history.rest.HistoryRestClient;
import com.ganjarramadhan.bookdoctor.pojo.Booking;
import com.ganjarramadhan.bookdoctor.pojo.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ganjarramadhan on 4/11/16.
 */
public class HistoryModel implements HistoryModelInterface {

    @Override
    public void loadHistoryData(User user, final OnLoadHistoryFinishedListener listener) {

        Call<List<Booking>> history = HistoryRestClient.get().getBookingHistory(user.getId());
        history.enqueue(new Callback<List<Booking>>() {
            @Override
            public void onResponse(Call<List<Booking>> call, Response<List<Booking>> response) {
                if (response.isSuccessful()){
                    listener.onLoadHistorySuccess(response.body());
                } else {
                    listener.onLoadHistoryFailed(response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Booking>> call, Throwable t) {
                listener.onLoadHistoryFailed(t.getMessage());
            }
        });

    }

    @Override
    public void cancelBooking(Booking booking, final OnCancelBookingListener listener) {
        Call<Booking> cancelBookingCall = HistoryRestClient.get().cancelBooking(booking.getId());
        cancelBookingCall.enqueue(new Callback<Booking>() {
            @Override
            public void onResponse(Call<Booking> call, Response<Booking> response) {
                if (response.isSuccessful())
                    listener.onCancelSuccess(response.body());
                else
                    listener.onCancelFailed(response.message());
            }

            @Override
            public void onFailure(Call<Booking> call, Throwable t) {
                listener.onCancelFailed(t.getMessage());
            }
        });
    }
}
