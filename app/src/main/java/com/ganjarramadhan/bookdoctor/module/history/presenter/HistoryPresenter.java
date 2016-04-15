package com.ganjarramadhan.bookdoctor.module.history.presenter;

import android.content.Context;
import android.content.SharedPreferences;

import com.ganjarramadhan.bookdoctor.R;
import com.ganjarramadhan.bookdoctor.module.history.model.HistoryModel;
import com.ganjarramadhan.bookdoctor.module.history.presenter.listener.OnCancelBookingListener;
import com.ganjarramadhan.bookdoctor.module.history.presenter.listener.OnLoadHistoryFinishedListener;
import com.ganjarramadhan.bookdoctor.module.history.view.HistoryInterface;
import com.ganjarramadhan.bookdoctor.module.history.view.listener.OnHistoryItemClickListener;
import com.ganjarramadhan.bookdoctor.pojo.Booking;
import com.ganjarramadhan.bookdoctor.pojo.User;
import com.ganjarramadhan.bookdoctor.util.AppsConstant;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by ganjarramadhan on 4/11/16.
 */
public class HistoryPresenter implements HistoryPresenterInterface,
        OnLoadHistoryFinishedListener, OnCancelBookingListener,
        OnHistoryItemClickListener {

    private WeakReference<HistoryInterface> view;
    private HistoryModel model;

    public HistoryPresenter(HistoryInterface view) {
        this.view = new WeakReference<HistoryInterface>(view);
        this.model = new HistoryModel();
    }

    @Override
    public void loadHistory(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(context.getResources().getString(R.string.app_name), Context.MODE_PRIVATE);
        User user = new User();
        user.setId(prefs.getInt(AppsConstant.KEY_USER_ID, 0));

        model.loadHistoryData(user, this);

    }

    @Override
    public void cancelBooking(Context context, Booking booking) {
        model.cancelBooking(booking, this);
    }

    @Override
    public void onLoadHistorySuccess(List<Booking> listBooking) {
        if (null != view.get()) view.get().onLoadHistorySuccess(listBooking);
    }

    @Override
    public void onLoadHistoryFailed(String message) {
        if (null != view.get()) view.get().onLoadHistoryFailed(message);
    }

    @Override
    public void onListBookingItemClicked(Booking booking) {
        // do nothing
    }

    @Override
    public void onBtnCancelBookingClicked(Booking booking) {
        if (null != view.get()) view.get().onCancelBooking(booking);
    }

    @Override
    public void onCancelSuccess(Booking booking) {
        if (null != view.get()) view.get().onCancelBookingSuccess(booking);
    }

    @Override
    public void onCancelFailed(String message) {
        if (null != view.get()) view.get().onCancelBookingFailed(message);
    }
}
