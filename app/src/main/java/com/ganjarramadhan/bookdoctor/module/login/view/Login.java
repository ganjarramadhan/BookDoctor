package com.ganjarramadhan.bookdoctor.module.login.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ganjarramadhan.bookdoctor.R;
import com.ganjarramadhan.bookdoctor.module.dashboard.view.Dashboard;
import com.ganjarramadhan.bookdoctor.module.forgotpassword.view.ForgotPassword;
import com.ganjarramadhan.bookdoctor.module.login.presenter.LoginPresenter;
import com.ganjarramadhan.bookdoctor.module.signup.view.SignUp;
import com.ganjarramadhan.bookdoctor.pojo.User;
import com.ganjarramadhan.bookdoctor.util.AppsConstant;
import com.ganjarramadhan.bookdoctor.util.AppsUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

public class Login extends AppCompatActivity implements LoginInterface {

    @Bind(R.id.activity_login_toolbar) Toolbar toolbar;
    @Bind(R.id.activity_login_coordinator_layout) CoordinatorLayout coordinatorLayout;

    @Bind(R.id.activity_login_et_email) EditText etEmail;
    @Bind(R.id.activity_login_et_password) EditText etPassword;

    @Bind(R.id.activity_login_tv_sign_up) TextView tvSignUp;
    @Bind(R.id.activity_login_tv_forgot_password) TextView tvForgotPassword;

    @Bind(R.id.activity_login_btn_login) AppCompatButton btnLogin;

    @Bind(R.id.activity_login_layout_loading) View layoutLoading;
    @Bind(R.id.activity_login_layout_content) View layoutContent;

    LoginPresenter presenter;
    AlertDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // init ui
        setContentView(R.layout.activity_login);

        // start binding using butter knife
        ButterKnife.bind(this);

        // set action bar
        setSupportActionBar(toolbar);

        // set title
        getSupportActionBar().setTitle(R.string.title_activity_login);

        // display up indicator
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // create loading dialog
        loadingDialog = AppsUtil.showLoadingDialog(this, R.style.DialogTheme, R.layout.layout_loading);

        // init presenter
        presenter = new LoginPresenter(this);

        // hide content
        showContent(true);
    }

    @OnClick(R.id.activity_login_btn_login)
    public void login(View v){
        // show loading
        loadingDialog.show();

        // get email and password from edit text
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        // do a simple validation
        if (email.isEmpty() || !AppsUtil.isValidEmail(email)){
            Snackbar.make(coordinatorLayout, "Email is not valid", Snackbar.LENGTH_SHORT).show();
        } else if (password.isEmpty() || password.toString().length() < 6) {
            Snackbar.make(coordinatorLayout, "Password length must be at least six (6) characters", Snackbar.LENGTH_SHORT).show();
        } else {
            presenter.onLoginClicked(this, email, password);
        }
    }

    @OnClick(R.id.activity_login_tv_sign_up)
    public void signUp(View v){
        presenter.onSignUpClicked();
    }

    @OnClick(R.id.activity_login_tv_forgot_password)
    public void forgotPassword(){
        presenter.onForgotPasswordClicked();
    }

    @Override
    public void onLoginFailed(String message) {
        loadingDialog.dismiss();
        Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onLoginSuccess(User user) {
        SharedPreferences prefs = getSharedPreferences(getResources().getString(R.string.app_name), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(AppsConstant.KEY_USER_ID, user.getId());
        editor.putString(AppsConstant.KEY_EMAIL, user.getEmail());
        editor.putInt(AppsConstant.KEY_USER_TYPE, user.getType());
        editor.putString(AppsConstant.KEY_FIRST_NAME, user.getFirstName());
        editor.putString(AppsConstant.KEY_LAST_NAME, user.getLastName());
        editor.putString(AppsConstant.KEY_AVATAR, user.getAvatarUrl());
        editor.putString(AppsConstant.KEY_PASSWORD, user.getPassword());
        editor.commit();
        loadingDialog.dismiss();
        Timber.d("Prefs Email: " + prefs.getString(AppsConstant.KEY_EMAIL, "NO EMAIL"));
        Snackbar.make(coordinatorLayout, "Login Success", Snackbar.LENGTH_LONG).show();
        finish();
        Intent intent = new Intent(Login.this, Dashboard.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void goToSignUpScreen() {
        startActivity(new Intent(Login.this, SignUp.class));
    }

    @Override
    public void goToForgotPasswordScreen() {
        startActivity(new Intent(Login.this, ForgotPassword.class));
    }

    private void showContent(boolean yes){
        if (yes){
            layoutContent.setVisibility(View.VISIBLE);
            layoutLoading.setVisibility(View.GONE);
        } else {
            layoutContent.setVisibility(View.GONE);
            layoutLoading.setVisibility(View.VISIBLE);
        }
    }

}
