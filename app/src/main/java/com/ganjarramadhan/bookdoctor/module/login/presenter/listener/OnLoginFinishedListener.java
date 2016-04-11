package com.ganjarramadhan.bookdoctor.module.login.presenter.listener;

import com.ganjarramadhan.bookdoctor.pojo.User;

/**
 * Created by ganjarramadhan on 4/9/16.
 */
public interface OnLoginFinishedListener {

    void onLoginSuccess(User user);
    void onLoginFailed(String message);

}
