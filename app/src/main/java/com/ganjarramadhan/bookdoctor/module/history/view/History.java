package com.ganjarramadhan.bookdoctor.module.history.view;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.ganjarramadhan.bookdoctor.R;
import com.ganjarramadhan.bookdoctor.module.booking.presenter.BookingPresenter;
import com.ganjarramadhan.bookdoctor.module.history.presenter.HistoryPresenter;
import com.ganjarramadhan.bookdoctor.module.history.view.listener.OnHistoryItemClickListener;
import com.ganjarramadhan.bookdoctor.pojo.Booking;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class History extends AppCompatActivity implements HistoryInterface, OnHistoryItemClickListener {

    @Bind(R.id.activity_history_toolbar) Toolbar toolbar;
    @Bind(R.id.activity_history_coordinator) CoordinatorLayout coordinatorLayout;

    @Bind(R.id.activity_history_rv_history) RecyclerView rvHistory;

    HistoryPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // load layout
        setContentView(R.layout.activity_history);

        // inject butter knife
        ButterKnife.bind(this);

        // set toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // init presenter
        presenter = new HistoryPresenter(this);
    }

    @Override
    public void onLoadHistorySuccess(List<Booking> listBooking) {

    }

    @Override
    public void onLoadHistoryFailed(String message) {

    }

    @Override
    public void onListBookingItemClicked(Booking booking) {

    }
}
