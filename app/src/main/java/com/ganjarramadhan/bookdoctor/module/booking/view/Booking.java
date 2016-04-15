package com.ganjarramadhan.bookdoctor.module.booking.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ganjarramadhan.bookdoctor.R;
import com.ganjarramadhan.bookdoctor.module.booking.presenter.BookingPresenter;
import com.ganjarramadhan.bookdoctor.module.login.view.Login;
import com.ganjarramadhan.bookdoctor.pojo.User;
import com.ganjarramadhan.bookdoctor.util.AppsConstant;
import com.ganjarramadhan.bookdoctor.util.AppsUtil;
import com.ganjarramadhan.bookdoctor.util.MyDatePickerDialog;
import com.ganjarramadhan.bookdoctor.util.TimeListAdapter;

import org.parceler.Parcels;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import timber.log.Timber;

public class Booking extends AppCompatActivity implements BookingInterface{

    @Bind(R.id.activity_booking_toolbar) Toolbar toolbar;
    @Bind(R.id.activity_booking_coordinator) CoordinatorLayout coordinatorLayout;

    @Bind(R.id.activity_booking_tv_name) TextView tvName;
    @Bind(R.id.activity_booking_image_avatar) CircleImageView imgAvatar;
    @Bind(R.id.activity_booking_et_date) EditText etBookingDate;
    @Bind(R.id.activity_booking_et_time) EditText etBookingTime;
    @Bind(R.id.activity_booking_btn_book) AppCompatButton btnBook;
    @Bind(R.id.activity_booking_img_btn_calendar) AppCompatImageButton imgBtnCalendar;
    @Bind(R.id.activity_booking_img_btn_time) AppCompatImageButton imgBtnTime;

    @Bind(R.id.activity_booking_layout_content) View layoutContent;
    @Bind(R.id.activity_booking_layout_loading) View layoutLoading;

    BookingPresenter presenter;
    User doctor, patient;
    AlertDialog loadingDialog;

    // helper
    Date bookingDate;
    int bookingTimeIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // load layout
        setContentView(R.layout.activity_booking);

        // inject
        ButterKnife.bind(this);

        // set toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // init presenter
        presenter = new BookingPresenter(this);

        // loading
        showContent(false);

        // create loading dialog
        loadingDialog = AppsUtil.showLoadingDialog(this, R.style.DialogTheme, R.layout.layout_loading);

        // get data bundle
        User user = Parcels.unwrap(getIntent().getParcelableExtra(AppsConstant.KEY_USER));
        populateUI(user);

    }

    @Override
    public void onBookingSuccess(com.ganjarramadhan.bookdoctor.pojo.Booking booking) {
        loadingDialog.dismiss();
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.DialogTheme);
        builder.setTitle(getString(R.string.app_name));
        builder.setMessage("Booking Success");
        builder.setCancelable(false);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                finish();
            }
        });
        builder.show();
    }

    @Override
    public void onBookingFailed(String message) {
        loadingDialog.dismiss();
        Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onCheckingScheduleSuccess(List<Integer> listOfUnavailableTime) {
        loadingDialog.dismiss();
        showTimePicker(listOfUnavailableTime);
    }

    @Override
    public void onCheckingScheduleFailed(String message) {
        loadingDialog.dismiss();
        Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onUserNotLoggedIn(String message) {
        loadingDialog.dismiss();
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.DialogTheme);
        builder.setTitle(getString(R.string.user_not_logged_in));
        builder.setMessage(message);
        builder.setCancelable(false);
        builder.setPositiveButton("Login", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                finish();
                startActivity(new Intent(Booking.this, Login.class));
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();

    }

    @Override
    public void populateUI(User user) {
        this.doctor = user;
        if (null != user){

            Glide.with(this).load(AppsConstant.BASE_DOCTOR_IMAGE_URL + user.getAvatarUrl()).into(imgAvatar);
            tvName.setText(user.getFirstName() + " " + user.getLastName());

        }

        showContent(true);

    }

    @OnClick(R.id.activity_booking_btn_book)
    public void doBooking(View v){

        if(bookingDate == null || bookingTimeIndex < 0){
            Snackbar.make(coordinatorLayout, R.string.please_select_date_time,
                    Snackbar.LENGTH_LONG).show();
            return;
        }
        loadingDialog.show();
        presenter.submitBooking(this, bookingDate, bookingTimeIndex, doctor);
    }

    @OnClick(R.id.activity_booking_img_btn_calendar)
    public void showDatePicker(View v){
        Timber.d("Booking Date");

        List<Integer> listOfAvailableDay = doctor.getListAvailableDays();

        // check is today available
        Calendar now = Calendar.getInstance();
        for (int i=0; i<7; i++){
            if (listOfAvailableDay.contains(now.get(Calendar.DAY_OF_WEEK))) break;

            now.add(Calendar.DATE, 1);
        }

        MyDatePickerDialog dpd = MyDatePickerDialog.newInstance(
                new MyDatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(MyDatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                        Timber.d("Date: " + dayOfMonth + ". Month: " + monthOfYear + ". Year: " + year);
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(Calendar.DATE, dayOfMonth);
                        calendar.set(Calendar.MONTH, monthOfYear);
                        calendar.set(Calendar.YEAR, year);
                        bookingDate = calendar.getTime();
                        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd MMMM yyyy");
                        etBookingDate.setText(sdf.format(bookingDate));
                    }
                },
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH),
                listOfAvailableDay
        );
        dpd.vibrate(false);
        dpd.setAccentColor(ContextCompat.getColor(this, R.color.colorAccent));
        dpd.setMinDate(Calendar.getInstance());
        dpd.show(getFragmentManager(), "Booking Date");
    }

    @OnClick(R.id.activity_booking_img_btn_time)
    public void showTimePicker(View v){
        if (bookingDate == null){
            Snackbar.make(coordinatorLayout, R.string.please_select_date, Snackbar.LENGTH_LONG).show();
            return;
        }
        loadingDialog.show();
        presenter.checkSchedule(bookingDate, doctor);
    }

    private void showTimePicker(List<Integer> unavailableTimeIndex){

        final String[] timeArray = getResources().getStringArray(R.array.time_array);

        TimeListAdapter adapter = new TimeListAdapter(this, android.R.layout.simple_list_item_single_choice,
                timeArray, unavailableTimeIndex, bookingDate);

        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.DialogTheme);
        builder.setSingleChoiceItems(adapter, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Timber.d(timeArray[which]);
                bookingTimeIndex = which;
            }
        });
        builder.setTitle("Booking Time");
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                etBookingTime.setText(timeArray[bookingTimeIndex]);
                dialog.dismiss();
            }
        });
        builder.show();
    }

    private void showContent(boolean yes){
        if (yes){
            layoutContent.setVisibility(View.VISIBLE);
            layoutLoading.setVisibility(View.GONE);
        } else {
            layoutLoading.setVisibility(View.VISIBLE);
            layoutContent.setVisibility(View.GONE);
        }
    }

}
