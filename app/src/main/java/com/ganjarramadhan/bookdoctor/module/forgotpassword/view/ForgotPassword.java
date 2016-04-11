package com.ganjarramadhan.bookdoctor.module.forgotpassword.view;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.ganjarramadhan.bookdoctor.R;
import com.ganjarramadhan.bookdoctor.module.forgotpassword.presenter.ForgotPasswordPresenter;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ForgotPassword extends AppCompatActivity implements ForgotPasswordInterface{

    @Bind(R.id.activity_forgot_password_toolbar) Toolbar toolbar;
    @Bind(R.id.activity_forgot_password_coordinator_layout) CoordinatorLayout coordinatorLayout;

    @Bind(R.id.activity_forgot_password_et_email) EditText etEmail;

    @Bind(R.id.activity_forgot_password_btn_submit) AppCompatButton btnSubmit;

    ForgotPasswordPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // init ui
        setContentView(R.layout.activity_forgot_password);

        // start binding using butter knife
        ButterKnife.bind(this);

        // set action bar
        setSupportActionBar(toolbar);

        // display up indicator
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // init presenter
        presenter = new ForgotPasswordPresenter(this);
    }

    @Override
    public void onForgotPasswordResult(String message) {

    }
}
