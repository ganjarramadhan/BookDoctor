package com.ganjarramadhan.bookdoctor.module.signup.presenter.listener;

import com.ganjarramadhan.bookdoctor.pojo.User;

/**
 * Created by ganjarramadhan on 4/11/16.
 */
public interface OnRegisteringUserFinishedListener {

    void onRegisterSuccess(User user);
    void onRegisterFailed(String message);

}
