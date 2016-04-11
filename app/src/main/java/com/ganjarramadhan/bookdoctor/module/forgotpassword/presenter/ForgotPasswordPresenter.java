package com.ganjarramadhan.bookdoctor.module.forgotpassword.presenter;

import com.ganjarramadhan.bookdoctor.module.forgotpassword.model.ForgotPasswordModel;
import com.ganjarramadhan.bookdoctor.module.forgotpassword.view.ForgotPasswordInterface;
import com.ganjarramadhan.bookdoctor.module.login.presenter.listener.OnForgotPasswordFinishedListener;

import java.lang.ref.WeakReference;

/**
 * Created by ganjarramadhan on 4/11/16.
 */
public class ForgotPasswordPresenter implements ForgotPasswordPresenterInterface, OnForgotPasswordFinishedListener{

    private WeakReference<ForgotPasswordInterface> view;
    private ForgotPasswordModel model;

    public ForgotPasswordPresenter(ForgotPasswordInterface view){
        this.view = new WeakReference<ForgotPasswordInterface>(view);
        model = new ForgotPasswordModel();
    }

    @Override
    public void submitForgotPassword(String email) {
        model.forgotPasswordRequest(email, this);
    }

    @Override
    public void onForgotPasswordFinish(String message) {
        if (view.get() != null) view.get().onForgotPasswordResult(message);
    }

}
