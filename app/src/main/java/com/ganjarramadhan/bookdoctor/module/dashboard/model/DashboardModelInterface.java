package com.ganjarramadhan.bookdoctor.module.dashboard.model;

import com.ganjarramadhan.bookdoctor.module.dashboard.presenter.listener.OnLoadUserFinished;

/**
 * Created by ganjarramadhan on 4/11/16.
 */
public interface DashboardModelInterface {

    void loadUsersData(OnLoadUserFinished listener);

}
