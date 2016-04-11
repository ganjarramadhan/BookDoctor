package com.ganjarramadhan.bookdoctor.module.signup.model;

import com.ganjarramadhan.bookdoctor.module.signup.presenter.listener.OnRegisteringUserFinishedListener;
import com.ganjarramadhan.bookdoctor.pojo.User;

/**
 * Created by ganjarramadhan on 4/9/16.
 */
public class SignUpModel implements SignUpModelInterface {

    @Override
    public void registeringUserToServer(User user, OnRegisteringUserFinishedListener listener) {
        listener.onRegisterFailed("");
    }
}
