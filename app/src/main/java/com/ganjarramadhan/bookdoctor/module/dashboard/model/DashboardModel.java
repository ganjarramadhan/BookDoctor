package com.ganjarramadhan.bookdoctor.module.dashboard.model;

import com.ganjarramadhan.bookdoctor.module.dashboard.presenter.listener.OnLoadUserFinished;
import com.ganjarramadhan.bookdoctor.pojo.User;

import java.util.ArrayList;

/**
 * Created by ganjarramadhan on 4/9/16.
 */
public class DashboardModel implements DashboardModelInterface {
    @Override
    public void loadUsersData(OnLoadUserFinished listener) {
        listener.onLoadUsersDataSuccess(new ArrayList<User>());
    }
}
