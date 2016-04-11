package com.ganjarramadhan.bookdoctor.module.login.view;

import com.ganjarramadhan.bookdoctor.pojo.User;

/**
 * Created by ganjarramadhan on 4/9/16.
 */
public interface LoginInterface {

    void onLoginFailed(String message);
    void onLoginSuccess();
    void onSignUpFailed(String message);
    void onSignUpSuccess();
    void onForgotPasswordFailed();
    void onForgotPasswordSuccess();

}
