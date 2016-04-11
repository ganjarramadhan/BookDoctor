package com.ganjarramadhan.bookdoctor.module.signup.view;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;

import com.ganjarramadhan.bookdoctor.R;
import com.ganjarramadhan.bookdoctor.module.signup.presenter.SignUpPresenter;
import com.ganjarramadhan.bookdoctor.pojo.User;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SignUp extends AppCompatActivity implements SignUpInterface {

    @Bind(R.id.activity_sign_up_toolbar) Toolbar toolbar;
    @Bind(R.id.activity_sign_up_coordinator_layout) CoordinatorLayout coordinatorLayout;

    @Bind(R.id.activity_sign_up_et_full_name) EditText etFullname;
    @Bind(R.id.activity_sign_up_et_email) EditText etEmail;
    @Bind(R.id.activity_sign_up_et_password) EditText etPassword;
    @Bind(R.id.activity_sign_up_et_repeat_password) EditText etRepeatPassword;

    @Bind(R.id.activity_sign_up_btn_sign_up) AppCompatButton btnSignUp;

    SignUpPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // init ui
        setContentView(R.layout.activity_sign_up);

        // start binding using butter knife
        ButterKnife.bind(this);

        // set action bar
        setSupportActionBar(toolbar);

        // display up indicator
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // init presenter
        presenter = new SignUpPresenter(this);

    }

    @Override
    public void onRegisterSuccess(User user) {

    }

    @Override
    public void onRegisterFailed(String message) {

    }
}
