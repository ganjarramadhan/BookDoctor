package com.ganjarramadhan.bookdoctor.module.signup.view;

import com.ganjarramadhan.bookdoctor.pojo.User;

/**
 * Created by ganjarramadhan on 4/9/16.
 */
public interface SignUpInterface {

    void onRegisterSuccess(User user);
    void onRegisterFailed(String message);

}
