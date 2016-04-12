package com.ganjarramadhan.bookdoctor.module.userprofile.presenter.listener;

import com.ganjarramadhan.bookdoctor.pojo.User;

/**
 * Created by ganjarramadhan on 4/11/16.
 */
public interface OnSaveUserFinishedListener {

    void onSaveUserSuccess(User user);
    void onSaveUserFailed(String message);

}
