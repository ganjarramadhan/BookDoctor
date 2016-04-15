package com.ganjarramadhan.bookdoctor.module.dashboard.presenter;

import android.content.Context;

import com.ganjarramadhan.bookdoctor.pojo.User;

import java.util.List;

/**
 * Created by ganjarramadhan on 4/11/16.
 */
public interface DashboardPresenterInterface {

    void loadUsers(Context context);
    void goToLogin();

    User getLoggedInUser(Context context);
    void userLogout(Context context);

}
