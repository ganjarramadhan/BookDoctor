package com.ganjarramadhan.bookdoctor.module.signup.view;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.ganjarramadhan.bookdoctor.R;
import com.ganjarramadhan.bookdoctor.module.signup.presenter.SignUpPresenter;
import com.ganjarramadhan.bookdoctor.pojo.User;
import com.ganjarramadhan.bookdoctor.util.AppsUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUp extends AppCompatActivity implements SignUpInterface {

    @Bind(R.id.activity_sign_up_toolbar) Toolbar toolbar;
    @Bind(R.id.activity_sign_up_coordinator_layout) CoordinatorLayout coordinatorLayout;

    @Bind(R.id.activity_sign_up_et_first_name) EditText etFirstName;
    @Bind(R.id.activity_sign_up_et_last_name) EditText etLastName;
    @Bind(R.id.activity_sign_up_et_email) EditText etEmail;
    @Bind(R.id.activity_sign_up_et_password) EditText etPassword;
    @Bind(R.id.activity_sign_up_et_repeat_password) EditText etRepeatPassword;

    @Bind(R.id.activity_sign_up_btn_sign_up) AppCompatButton btnSignUp;

    SignUpPresenter presenter;
    AlertDialog loadingDialog;

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

        // create loading dialog
        loadingDialog = AppsUtil.showLoadingDialog(this, R.style.DialogTheme, R.layout.layout_loading);


        // init presenter
        presenter = new SignUpPresenter(this);

    }

    @Override
    public void onRegisterSuccess(User user) {
        loadingDialog.dismiss();
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.DialogTheme);
        builder.setTitle(getString(R.string.app_name));
        builder.setMessage("Sign up success");
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
    public void onRegisterFailed(String message) {
        loadingDialog.dismiss();
        Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_SHORT).show();
    }

    @OnClick(R.id.activity_sign_up_btn_sign_up)
    public void onSignUpClick(View v){

        String email = etEmail.getText().toString();
        String firstName = etFirstName.getText().toString();
        String lastName = etLastName.getText().toString();
        String password = etPassword.getText().toString();
        String repeatPassword = etRepeatPassword.getText().toString();

        if (email.equals("") || !AppsUtil.isValidEmail(email)) {
            etEmail.setError("This field can not be empty or invalid email address");
            return;
        } else if (firstName.equals("") || firstName.length() < 6){
            etFirstName.setError("This field is required and the minimum length is 6");
            return;
        } else if (lastName.equals("") || lastName.length() < 6){
            etLastName.setError("This field is required and the minimum length is 6");
            return;
        } else if (password.equals("") || password.length() < 6){
            etPassword.setError("This field is required and the minimum length is 6");
            return;
        } else if (!repeatPassword.equals(password)){
            etRepeatPassword.setError("Password not match");
            return;
        }

        loadingDialog.show();
        User user = new User(0, email, firstName, lastName, password, User.GROUP_CUSTOMER);
        presenter.signUpUser(user);

    }
}
