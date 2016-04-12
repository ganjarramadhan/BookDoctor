package com.ganjarramadhan.bookdoctor.module.userprofile.model;

import com.ganjarramadhan.bookdoctor.module.userprofile.presenter.listener.OnSaveUserFinishedListener;
import com.ganjarramadhan.bookdoctor.pojo.User;

/**
 * Created by ganjarramadhan on 4/11/16.
 */
public interface UserProfileModelInterface {

    void saveUserToServer(User user, OnSaveUserFinishedListener listener);

}
