package com.ganjarramadhan.bookdoctor.module.login.model;

import com.ganjarramadhan.bookdoctor.module.login.presenter.listener.OnForgotPasswordFinishedListener;
import com.ganjarramadhan.bookdoctor.module.login.presenter.listener.OnLoginFinishedListener;
import com.ganjarramadhan.bookdoctor.module.login.presenter.listener.OnSignUpFinishedListener;
import com.ganjarramadhan.bookdoctor.pojo.User;

/**
 * Created by ganjarramadhan on 4/9/16.
 */
public class LoginModel implements LoginModelInterface {


    @Override
    public void checkUser(String email, String password, OnLoginFinishedListener listener) {
        if (email.equals("ganjar.ramadhan05@gmail.com") && password.equals("password")){
            User user = new User(1, email, password, "Ganjar Ramadhan", "", User.TYPE_CUSTOMER);
            listener.onLoginSuccess(user);
        } else {
            listener.onLoginFailed("Login Failed");
        }
    }

    @Override
    public void signUpUser(User user, OnSignUpFinishedListener listener) {

    }

    @Override
    public void forgotPassword(String email, OnForgotPasswordFinishedListener listener) {

    }
}
