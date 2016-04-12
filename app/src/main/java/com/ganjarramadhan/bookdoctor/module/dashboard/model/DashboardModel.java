package com.ganjarramadhan.bookdoctor.module.dashboard.model;

import com.ganjarramadhan.bookdoctor.module.dashboard.presenter.listener.OnLoadUserFinished;
import com.ganjarramadhan.bookdoctor.pojo.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ganjarramadhan on 4/9/16.
 */
public class DashboardModel implements DashboardModelInterface {

    @Override
    public void loadUsersData(OnLoadUserFinished listener) {
        List<User> listUsers = new ArrayList<>();
        for (int i=0; i<10; i++){
            listUsers.add(new User(i+1, "ganjar" + i + "@gmail.com", "Ganjar Ramadhan " + i,
                    "https://0.academia-photos.com/17288193/4787443/5515080/s200_ganjar.ramadhan.jpg", User.TYPE_DOCTOR));
        }
        listener.onLoadUsersDataSuccess(listUsers);
    }
}
