package com.ganjarramadhan.bookdoctor.module.dashboard.model;

import com.ganjarramadhan.bookdoctor.module.dashboard.presenter.listener.OnLoadDoctorScheduleListener;
import com.ganjarramadhan.bookdoctor.module.dashboard.presenter.listener.OnLoadUserFinished;
import com.ganjarramadhan.bookdoctor.module.dashboard.rest.DashboardAPIURLConstant;
import com.ganjarramadhan.bookdoctor.module.dashboard.rest.DashboardRestClient;
import com.ganjarramadhan.bookdoctor.pojo.Booking;
import com.ganjarramadhan.bookdoctor.pojo.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ganjarramadhan on 4/9/16.
 */
public class DashboardModel implements DashboardModelInterface {

    @Override
    public void loadUsersData(final OnLoadUserFinished listener) {

        Call<List<User>> users = DashboardRestClient.get().getUsers(DashboardAPIURLConstant.GROUP_DOCTOR, 1);
        users.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {

                if (response.isSuccessful()) {
                    List<User> listOfUser = response.body();
                    listener.onLoadUsersDataSuccess(listOfUser);
                } else {
                    listener.onLoadUsersDataFailed("Failed to get users data.");
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                listener.onLoadUsersDataFailed(t.getMessage());
            }
        });

    }

    @Override
    public void loadDoctorsSchedule(User doctor, final OnLoadDoctorScheduleListener listener) {
        Call<List<Booking>> doctorsHistory = DashboardRestClient.get().getDoctorsHistory(doctor.getId());
        doctorsHistory.enqueue(new Callback<List<Booking>>() {
            @Override
            public void onResponse(Call<List<Booking>> call, Response<List<Booking>> response) {
                if (response.isSuccessful()){
                    listener.onLoadDoctorsScheduleSuccess(response.body());
                } else {
                    listener.onLoadDoctorsScheduleFailed(response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Booking>> call, Throwable t) {
                listener.onLoadDoctorsScheduleFailed(t.getMessage());
            }
        });
    }
}
