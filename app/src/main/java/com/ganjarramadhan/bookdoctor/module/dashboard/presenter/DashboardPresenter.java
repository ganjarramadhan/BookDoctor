package com.ganjarramadhan.bookdoctor.module.dashboard.presenter;

import com.ganjarramadhan.bookdoctor.module.dashboard.model.DashboardModel;
import com.ganjarramadhan.bookdoctor.module.dashboard.presenter.listener.OnLoadUserFinished;
import com.ganjarramadhan.bookdoctor.module.dashboard.view.Dashboard;
import com.ganjarramadhan.bookdoctor.pojo.User;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by ganjarramadhan on 4/9/16.
 */
public class DashboardPresenter implements DashboardPresenterInterface, OnLoadUserFinished{

    private WeakReference<Dashboard> view;
    private DashboardModel model;

    public DashboardPresenter(Dashboard view) {
        this.view = new WeakReference<Dashboard>(view);
        this.model = new DashboardModel();
    }

    @Override
    public void loadUsers() {
        model.loadUsersData(this);
    }

    @Override
    public void onLoadUsersDataSuccess(List<User> userList) {
        if (view.get() != null) view.get().onUsersDataAvailable(userList);
    }

    @Override
    public void onLoadUsersDataFailed(String message) {
        if (view.get() != null) view.get().onUsersDataNotAvailable(message);
    }
}
