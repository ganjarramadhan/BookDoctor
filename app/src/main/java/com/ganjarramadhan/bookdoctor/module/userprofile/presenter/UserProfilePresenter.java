package com.ganjarramadhan.bookdoctor.module.userprofile.presenter;

import com.ganjarramadhan.bookdoctor.module.userprofile.model.UserProfileModel;
import com.ganjarramadhan.bookdoctor.module.userprofile.presenter.listener.OnSaveUserFinishedListener;
import com.ganjarramadhan.bookdoctor.module.userprofile.view.UserProfileInterface;
import com.ganjarramadhan.bookdoctor.pojo.User;

import java.lang.ref.WeakReference;

/**
 * Created by ganjarramadhan on 4/11/16.
 */
public class UserProfilePresenter implements UserProfilePresenterInterface, OnSaveUserFinishedListener {

    private WeakReference<UserProfileInterface> view;
    private UserProfileModel model;

    public UserProfilePresenter(UserProfileInterface view) {
        this.view = new WeakReference<UserProfileInterface>(view);
    }

    @Override
    public void onSaveUserSuccess(User user) {
        if (null != view.get()) view.get().onSaveProfileSuccess(user);
    }

    @Override
    public void onSaveUserFailed(String message) {
        if (null != view.get()) view.get().onSaveProfileFailed(message);
    }

    @Override
    public void saveUser(User user) {
        model.saveUserToServer(user, this);
    }
}
