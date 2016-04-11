package com.ganjarramadhan.bookdoctor.module.dashboard.view;

import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.ganjarramadhan.bookdoctor.R;
import com.ganjarramadhan.bookdoctor.module.dashboard.presenter.DashboardPresenter;
import com.ganjarramadhan.bookdoctor.pojo.User;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Dashboard extends AppCompatActivity implements DashboardInterface{

    @Bind(R.id.activity_sign_up_toolbar) Toolbar toolbar;
    @Bind(R.id.activity_sign_up_coordinator_layout) CoordinatorLayout coordinatorLayout;

    @Bind(R.id.activity_dashboard_rv_user) RecyclerView rvUser;

    DashboardPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // load ui
        setContentView(R.layout.activity_dashboard);

        // set action bar
        setSupportActionBar(toolbar);

        // display up indicator
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // inject butter knife
        ButterKnife.bind(this);

        // init presenter
        presenter = new DashboardPresenter(this);

        // load data
        presenter.loadUsers();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.guest_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.guest_menu_login:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onListItemClick(User user) {

    }

    @Override
    public void onUsersDataAvailable(List<User> userList) {

    }

    @Override
    public void onUsersDataNotAvailable(String message) {

    }
}
