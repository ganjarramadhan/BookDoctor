package com.ganjarramadhan.bookdoctor.module.login.model;

import android.content.Context;

import com.ganjarramadhan.bookdoctor.R;
import com.ganjarramadhan.bookdoctor.module.login.presenter.listener.OnForgotPasswordFinishedListener;
import com.ganjarramadhan.bookdoctor.module.login.presenter.listener.OnLoginFinishedListener;
import com.ganjarramadhan.bookdoctor.module.login.presenter.listener.OnSignUpFinishedListener;
import com.ganjarramadhan.bookdoctor.module.login.rest.LoginRestClient;
import com.ganjarramadhan.bookdoctor.pojo.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ganjarramadhan on 4/9/16.
 */
public class LoginModel implements LoginModelInterface {


    @Override
    public void checkUser(final Context context, String email, String password, final OnLoginFinishedListener listener) {

        Call<User> doLogin = LoginRestClient.get().doLogin(email, password);
        doLogin.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    listener.onLoginSuccess(response.body());
                } else {
                    listener.onLoginFailed(context.getString(R.string.login_failed_message));
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                listener.onLoginFailed(t.getMessage());
            }
        });

    }

    @Override
    public void signUpUser(User user, OnSignUpFinishedListener listener) {

    }

    @Override
    public void forgotPassword(String email, OnForgotPasswordFinishedListener listener) {

    }
}
