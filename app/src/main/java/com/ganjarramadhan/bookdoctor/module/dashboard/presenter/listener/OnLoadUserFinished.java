package com.ganjarramadhan.bookdoctor.module.dashboard.presenter.listener;

import com.ganjarramadhan.bookdoctor.pojo.User;

import java.util.List;

/**
 * Created by ganjarramadhan on 4/11/16.
 */
public interface OnLoadUserFinished {

    void onLoadUsersDataSuccess(List<User> userList);
    void onLoadUsersDataFailed(String message);

}
