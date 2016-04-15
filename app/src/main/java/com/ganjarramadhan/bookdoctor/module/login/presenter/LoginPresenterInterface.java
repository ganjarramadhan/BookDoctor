package com.ganjarramadhan.bookdoctor.module.login.presenter;

import android.content.Context;

import com.ganjarramadhan.bookdoctor.pojo.User;

/**
 * Created by ganjarramadhan on 4/9/16.
 */
public interface LoginPresenterInterface {

    void onLoginClicked(Context context, String email, String password);
    void onSignUpClicked();
    void onForgotPasswordClicked();

}
