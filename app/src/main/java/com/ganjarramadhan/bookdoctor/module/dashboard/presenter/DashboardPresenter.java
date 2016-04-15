package com.ganjarramadhan.bookdoctor.module.dashboard.presenter;

import android.content.Context;
import android.content.SharedPreferences;

import com.ganjarramadhan.bookdoctor.R;
import com.ganjarramadhan.bookdoctor.module.dashboard.model.DashboardModel;
import com.ganjarramadhan.bookdoctor.module.dashboard.presenter.listener.OnDoctorsBookingItemClickListener;
import com.ganjarramadhan.bookdoctor.module.dashboard.presenter.listener.OnLoadDoctorScheduleListener;
import com.ganjarramadhan.bookdoctor.module.dashboard.presenter.listener.OnLoadUserFinished;
import com.ganjarramadhan.bookdoctor.module.dashboard.presenter.listener.OnUserListItemClick;
import com.ganjarramadhan.bookdoctor.module.dashboard.view.Dashboard;
import com.ganjarramadhan.bookdoctor.pojo.Booking;
import com.ganjarramadhan.bookdoctor.pojo.User;
import com.ganjarramadhan.bookdoctor.util.AppsConstant;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by ganjarramadhan on 4/9/16.
 */
public class DashboardPresenter implements DashboardPresenterInterface, OnLoadUserFinished,
        OnUserListItemClick, OnLoadDoctorScheduleListener, OnDoctorsBookingItemClickListener{

    private WeakReference<Dashboard> view;
    private DashboardModel model;

    public DashboardPresenter(Dashboard view) {
        this.view = new WeakReference<Dashboard>(view);
        this.model = new DashboardModel();
    }

    @Override
    public void loadUsers(Context context) {
        User user = getLoggedInUser(context);
        if (null != user && user.getType() == User.GROUP_DOCTOR){
            model.loadDoctorsSchedule(user, this);
        } else {
            model.loadUsersData(this);
        }
    }

    @Override
    public void goToLogin() {
        if (null != view.get()) view.get().goToLoginScreen();
    }

    @Override
    public User getLoggedInUser(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(context.getResources().getString(R.string.app_name), Context.MODE_PRIVATE);
        User user = new User();
        user.setId(prefs.getInt(AppsConstant.KEY_USER_ID, 0));
        user.setEmail(prefs.getString(AppsConstant.KEY_EMAIL, ""));
        user.setType(prefs.getInt(AppsConstant.KEY_USER_TYPE, 0));
        user.setFirstName(prefs.getString(AppsConstant.KEY_FIRST_NAME, ""));
        user.setLastName(prefs.getString(AppsConstant.KEY_LAST_NAME, ""));
        user.setAvatarUrl(prefs.getString(AppsConstant.KEY_AVATAR, ""));
        user.setPassword(prefs.getString(AppsConstant.KEY_PASSWORD, ""));
        return user;
    }

    @Override
    public void userLogout(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(context.getResources().getString(R.string.app_name), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.commit();

        if (null != view.get()) view.get().onUserLogout();
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

    @Override
    public void onLoadDoctorsScheduleSuccess(List<Booking> listOfDoctorsSchedule) {
        if (view.get() != null) view.get().onDoctorScheduleAvailable(listOfDoctorsSchedule);
    }

    @Override
    public void onLoadDoctorsScheduleFailed(String message) {
        if (view.get() != null) view.get().onDoctorScheduleNotAvailable(message);
    }

    @Override
    public void onListDoctorsBookingItemClicked(Booking booking) {
        // do nothing for now
    }
}
