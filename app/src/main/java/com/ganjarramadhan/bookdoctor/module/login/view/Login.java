package com.ganjarramadhan.bookdoctor.module.login.view;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ganjarramadhan.bookdoctor.BuildConfig;
import com.ganjarramadhan.bookdoctor.R;
import com.ganjarramadhan.bookdoctor.module.login.presenter.LoginPresenter;
import com.ganjarramadhan.bookdoctor.util.AppsUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Login extends AppCompatActivity implements LoginInterface {

    @Bind(R.id.activity_login_toolbar) Toolbar toolbar;
    @Bind(R.id.activity_login_coordinator_layout) CoordinatorLayout coordinatorLayout;

    @Bind(R.id.activity_login_et_email) EditText etEmail;
    @Bind(R.id.activity_login_et_password) EditText etPassword;

    @Bind(R.id.activity_login_tv_sign_up) TextView tvSignUp;
    @Bind(R.id.activity_login_tv_forgot_password) TextView tvForgotPassword;

    @Bind(R.id.activity_login_btn_login) AppCompatButton btnLogin;

    LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // init ui
        setContentView(R.layout.activity_login);

        // start binding using butter knife
        ButterKnife.bind(this);

        // set action bar
        setSupportActionBar(toolbar);

        // display up indicator
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // init presenter
        presenter = new LoginPresenter(this);

        if (BuildConfig.DEBUG){
            etEmail.setText("ganjar.ramadhan05@gmail.com");
            etPassword.setText("password");
        }
    }

    @OnClick(R.id.activity_login_btn_login)
    public void login(View v){

        // get email and password from edit text
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        // do a simple validation
        if (email.isEmpty() || !AppsUtil.isValidEmail(email)){
            Snackbar.make(coordinatorLayout, "Email is not valid", Snackbar.LENGTH_SHORT).show();
        } else if (password.isEmpty() || password.toString().length() < 6) {
            Snackbar.make(coordinatorLayout, "Password length must be at least six (6) characters", Snackbar.LENGTH_SHORT).show();
        } else {
            presenter.onLoginClicked(email, password);
        }
    }

    @OnClick(R.id.activity_login_tv_sign_up)
    public void signUp(){

    }

    @OnClick(R.id.activity_login_tv_forgot_password)
    public void forgotPassword(){

    }

    @Override
    public void onLoginFailed(String message) {
        Snackbar.make(coordinatorLayout, "Login Failed", Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onLoginSuccess() {
        Snackbar.make(coordinatorLayout, "Login Success", Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onSignUpFailed(String message) {

    }

    @Override
    public void onSignUpSuccess() {

    }

    @Override
    public void onForgotPasswordFailed() {

    }

    @Override
    public void onForgotPasswordSuccess() {

    }
}
