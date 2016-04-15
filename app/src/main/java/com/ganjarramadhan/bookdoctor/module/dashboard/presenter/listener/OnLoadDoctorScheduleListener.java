package com.ganjarramadhan.bookdoctor.module.dashboard.presenter.listener;

import com.ganjarramadhan.bookdoctor.pojo.Booking;

import java.util.List;

/**
 * Created by ganjarramadhan on 4/15/16.
 */
public interface OnLoadDoctorScheduleListener {

    void onLoadDoctorsScheduleSuccess(List<Booking> listOfDoctorsSchedule);
    void onLoadDoctorsScheduleFailed(String message);

}
