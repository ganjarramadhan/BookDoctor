package com.ganjarramadhan.bookdoctor.module.login.model;

import android.content.Context;

import com.ganjarramadhan.bookdoctor.module.login.presenter.listener.OnForgotPasswordFinishedListener;
import com.ganjarramadhan.bookdoctor.module.login.presenter.listener.OnLoginFinishedListener;
import com.ganjarramadhan.bookdoctor.module.login.presenter.listener.OnSignUpFinishedListener;
import com.ganjarramadhan.bookdoctor.pojo.User;

/**
 * Created by ganjarramadhan on 4/9/16.
 */
public interface LoginModelInterface {

    void checkUser(Context context, String email, String password, OnLoginFinishedListener listener);
    void signUpUser(User user, OnSignUpFinishedListener listener);
    void forgotPassword(String email, OnForgotPasswordFinishedListener listener);

}
