package com.ganjarramadhan.bookdoctor.module.login.presenter;

import com.ganjarramadhan.bookdoctor.module.login.model.LoginModel;
import com.ganjarramadhan.bookdoctor.module.login.presenter.listener.OnForgotPasswordFinishedListener;
import com.ganjarramadhan.bookdoctor.module.login.presenter.listener.OnLoginFinishedListener;
import com.ganjarramadhan.bookdoctor.module.login.presenter.listener.OnSignUpFinishedListener;
import com.ganjarramadhan.bookdoctor.module.login.view.LoginInterface;
import com.ganjarramadhan.bookdoctor.pojo.User;

import java.lang.ref.WeakReference;

/**
 * Created by ganjarramadhan on 4/9/16.
 */
public class LoginPresenter implements LoginPresenterInterface, OnLoginFinishedListener,
        OnSignUpFinishedListener, OnForgotPasswordFinishedListener {

    private WeakReference<LoginInterface> view;
    private LoginModel model;

    public LoginPresenter(LoginInterface view){
        this.view = new WeakReference<LoginInterface>(view);
        this.model = new LoginModel();
    }

    @Override
    public void onLoginClicked(String email, String password) {
        model.checkUser(email, password, this);
    }

    @Override
    public void onSignUpClicked() {
        if (null != view.get()) view.get().goToSignUpScreen();
    }

    @Override
    public void onForgotPasswordClicked() {
        if (null != view.get()) view.get().goToForgotPasswordScreen();
    }

    @Override
    public void onLoginSuccess(User user) {
        if (view.get() != null) view.get().onLoginSuccess();
    }

    @Override
    public void onLoginFailed(String message) {
        if (view.get() != null) view.get().onLoginFailed(message);
    }

    @Override
    public void onForgotPasswordFinish(String message) {

    }

    @Override
    public User onSignUpSuccess() {
        return null;
    }

    @Override
    public void onSignUpFailed(String message) {

    }
}
