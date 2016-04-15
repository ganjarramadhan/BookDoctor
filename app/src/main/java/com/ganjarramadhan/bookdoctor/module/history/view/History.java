package com.ganjarramadhan.bookdoctor.module.history.view;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.ganjarramadhan.bookdoctor.R;
import com.ganjarramadhan.bookdoctor.module.history.adapter.PatientHistoryListAdapter;
import com.ganjarramadhan.bookdoctor.module.history.presenter.HistoryPresenter;
import com.ganjarramadhan.bookdoctor.module.history.view.listener.OnHistoryItemClickListener;
import com.ganjarramadhan.bookdoctor.pojo.Booking;
import com.ganjarramadhan.bookdoctor.util.AppsUtil;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import timber.log.Timber;

public class History extends AppCompatActivity implements HistoryInterface {

    @Bind(R.id.activity_history_toolbar) Toolbar toolbar;
    @Bind(R.id.activity_history_coordinator) CoordinatorLayout coordinatorLayout;

    @Bind(R.id.activity_history_rv_history) RecyclerView rvHistory;

    @Bind(R.id.activity_history_content) View vContent;
    @Bind(R.id.activity_history_loading) View vLoading;

    HistoryPresenter presenter;
    AlertDialog loadingDialog;
    PatientHistoryListAdapter adapter;

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

        // loading
        loadingDialog = AppsUtil.showLoadingDialog(this, R.style.DialogTheme, R.layout.layout_loading);

        // init presenter
        presenter = new HistoryPresenter(this);

        // load data
        showContent(false);
        presenter.loadHistory(this);
    }

    @Override
    public void onLoadHistorySuccess(List<Booking> listBooking) {
        adapter = new PatientHistoryListAdapter(this, listBooking, presenter);
        rvHistory.setAdapter(adapter);
        showContent(true);
    }

    @Override
    public void onLoadHistoryFailed(String message) {
        showContent(true);
        AlertDialog.Builder builderAlert = new AlertDialog.Builder(this, R.style.DialogTheme);
        builderAlert.setMessage(message);
        builderAlert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builderAlert.show();
    }

    @Override
    public void onCancelBooking(final Booking booking) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this, R.style.DialogTheme);
        alert.setTitle(getString(R.string.confirmation));
        alert.setMessage(getString(R.string.confirm_cancel_booking_message));
        alert.setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                loadingDialog.show();
                Timber.d("Cancel Booking with Bookind ID: " + booking.getId());
                presenter.cancelBooking(History.this, booking);
            }
        });
        alert.setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alert.show();
    }

    @Override
    public void onCancelBookingSuccess(Booking booking) {
        loadingDialog.dismiss();
        if (null != adapter) {
            adapter.removeBookingObject(booking);
            Snackbar.make(coordinatorLayout, getString(R.string.cancel_success), Snackbar.LENGTH_LONG).show();
        }
    }

    @Override
    public void onCancelBookingFailed(String message) {
        loadingDialog.dismiss();
        Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_LONG).show();
    }

    private void showContent(boolean yes){
        if (yes){
            vContent.setVisibility(View.VISIBLE);
            vLoading.setVisibility(View.GONE);
        } else {
            vLoading.setVisibility(View.VISIBLE);
            vContent.setVisibility(View.GONE);
        }
    }

}
