package com.ganjarramadhan.bookdoctor.module.userprofile.view;

import com.ganjarramadhan.bookdoctor.pojo.User;

/**
 * Created by ganjarramadhan on 4/11/16.
 */
public interface UserProfileInterface {

    void onSaveProfileSuccess(User user);
    void onSaveProfileFailed(String message);

}
