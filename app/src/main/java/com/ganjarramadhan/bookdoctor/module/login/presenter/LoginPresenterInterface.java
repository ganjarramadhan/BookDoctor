package com.ganjarramadhan.bookdoctor.module.login.presenter;

import com.ganjarramadhan.bookdoctor.pojo.User;

/**
 * Created by ganjarramadhan on 4/9/16.
 */
public interface LoginPresenterInterface {

    void onLoginClicked(String email, String password);
    void onSignUpClicked(User user);
    void onForgotPasswordClicked(String email);

}
