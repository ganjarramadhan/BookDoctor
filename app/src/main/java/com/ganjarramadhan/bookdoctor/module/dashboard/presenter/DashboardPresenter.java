package com.ganjarramadhan.bookdoctor.module.dashboard.presenter;

import com.ganjarramadhan.bookdoctor.R;
import com.ganjarramadhan.bookdoctor.module.dashboard.model.DashboardModel;
import com.ganjarramadhan.bookdoctor.module.dashboard.presenter.listener.OnLoadUserFinished;
import com.ganjarramadhan.bookdoctor.module.dashboard.presenter.listener.OnUserListItemClick;
import com.ganjarramadhan.bookdoctor.module.dashboard.view.Dashboard;
import com.ganjarramadhan.bookdoctor.pojo.User;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by ganjarramadhan on 4/9/16.
 */
public class DashboardPresenter implements DashboardPresenterInterface, OnLoadUserFinished, OnUserListItemClick{

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
    public void goToLogin() {
        if (null != view.get()) view.get().goToLoginScreen();
    }

    @Override
    public void onLoadUsersDataSuccess(List<User> userList) {
        if (view.get() != null) view.get().onUsersDataAvailable(userList);
    }

    @Override
    public void onLoadUsersDataFailed(String message) {
        if (view.get() != null) view.get().onUsersDataNotAvailable(message);
    }

    @Override
    public void onUserItemClick(User user) {
        if (view.get() != null) view.get().onListItemClick(user);
    }
}
