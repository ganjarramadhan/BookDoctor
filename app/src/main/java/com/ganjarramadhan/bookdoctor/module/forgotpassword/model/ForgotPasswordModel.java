package com.ganjarramadhan.bookdoctor.module.forgotpassword.model;

import com.ganjarramadhan.bookdoctor.module.login.presenter.listener.OnForgotPasswordFinishedListener;

/**
 * Created by ganjarramadhan on 4/11/16.
 */
public class ForgotPasswordModel implements ForgotPasswordModelInterface {

    @Override
    public void forgotPasswordRequest(String email, OnForgotPasswordFinishedListener listener) {

        listener.onForgotPasswordFinish("Please check your email for reset your password.");

    }
}
