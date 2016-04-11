package com.ganjarramadhan.bookdoctor.module.signup.presenter;

import com.ganjarramadhan.bookdoctor.module.signup.model.SignUpModel;
import com.ganjarramadhan.bookdoctor.module.signup.presenter.listener.OnRegisteringUserFinishedListener;
import com.ganjarramadhan.bookdoctor.module.signup.view.SignUpInterface;
import com.ganjarramadhan.bookdoctor.pojo.User;

import java.lang.ref.WeakReference;

/**
 * Created by ganjarramadhan on 4/9/16.
 */
public class SignUpPresenter implements SignUpPresenterInterface, OnRegisteringUserFinishedListener {

    private WeakReference<SignUpInterface> view;
    private SignUpModel model;

    public SignUpPresenter(SignUpInterface view){
        this.view = new WeakReference<SignUpInterface>(view);
        this.model = new SignUpModel();
    }

    @Override
    public void signUpUser(User user) {
        model.registeringUserToServer(user, this);
    }

    @Override
    public void onRegisterSuccess(User user) {
        if (view.get() != null) view.get().onRegisterSuccess(user);
    }

    @Override
    public void onRegisterFailed(String message) {
        if (view.get() != null) view.get().onRegisterFailed(message);
    }
}
