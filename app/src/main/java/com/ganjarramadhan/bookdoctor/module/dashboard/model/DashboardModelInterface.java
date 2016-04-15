package com.ganjarramadhan.bookdoctor.module.dashboard.model;

import com.ganjarramadhan.bookdoctor.module.dashboard.presenter.listener.OnLoadDoctorScheduleListener;
import com.ganjarramadhan.bookdoctor.module.dashboard.presenter.listener.OnLoadUserFinished;
import com.ganjarramadhan.bookdoctor.pojo.User;

/**
 * Created by ganjarramadhan on 4/11/16.
 */
public interface DashboardModelInterface {

    void loadUsersData(OnLoadUserFinished listener);
    void loadDoctorsSchedule(User doctor, OnLoadDoctorScheduleListener listener);

}
