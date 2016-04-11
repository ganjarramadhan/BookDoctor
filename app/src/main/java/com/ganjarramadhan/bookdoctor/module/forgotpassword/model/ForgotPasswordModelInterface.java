package com.ganjarramadhan.bookdoctor.module.forgotpassword.model;

import com.ganjarramadhan.bookdoctor.module.login.presenter.listener.OnForgotPasswordFinishedListener;

/**
 * Created by ganjarramadhan on 4/11/16.
 */
public interface ForgotPasswordModelInterface {

    void forgotPasswordRequest(String email, OnForgotPasswordFinishedListener listener);

}
