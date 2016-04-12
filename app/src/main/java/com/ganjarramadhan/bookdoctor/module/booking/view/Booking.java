package com.ganjarramadhan.bookdoctor.module.booking.view;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ganjarramadhan.bookdoctor.R;
import com.ganjarramadhan.bookdoctor.module.booking.presenter.BookingPresenter;
import com.ganjarramadhan.bookdoctor.pojo.User;
import com.ganjarramadhan.bookdoctor.util.AppsConstant;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

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

    BookingPresenter presenter;

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

        // get data bundle
        User user = Parcels.unwrap(getIntent().getParcelableExtra(AppsConstant.KEY_USER));
        populateUI(user);

    }

    @Override
    public void onBookingSuccess(Booking booking) {

    }

    @Override
    public void onBookingFailed(String message) {

    }

    @Override
    public void populateUI(User user) {

        if (null != user){

            Glide.with(this).load(user.getAvatarUrl()).into(imgAvatar);
            tvName.setText(user.getFullName());

        }

    }

    @OnClick(R.id.activity_booking_btn_book)
    public void doBooking(View v){

    }

}
