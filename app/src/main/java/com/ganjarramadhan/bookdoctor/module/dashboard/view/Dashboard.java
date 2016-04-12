package com.ganjarramadhan.bookdoctor.module.dashboard.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.ganjarramadhan.bookdoctor.R;
import com.ganjarramadhan.bookdoctor.module.booking.view.Booking;
import com.ganjarramadhan.bookdoctor.module.dashboard.adapter.UserListAdapter;
import com.ganjarramadhan.bookdoctor.module.dashboard.presenter.DashboardPresenter;
import com.ganjarramadhan.bookdoctor.module.login.view.Login;
import com.ganjarramadhan.bookdoctor.pojo.User;
import com.ganjarramadhan.bookdoctor.util.AppsConstant;

import org.parceler.Parcels;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Dashboard extends AppCompatActivity implements DashboardInterface {

    @Bind(R.id.activity_dashboard_toolbar) Toolbar toolbar;
    @Bind(R.id.activity_dashboard_coordinator_layout) CoordinatorLayout coordinatorLayout;

    @Bind(R.id.activity_dashboard_rv_user) RecyclerView rvUser;

    DashboardPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // load ui
        setContentView(R.layout.activity_dashboard);

        // inject butter knife
        ButterKnife.bind(this);

        // set action bar
        setSupportActionBar(toolbar);

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
                presenter.goToLogin();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onListItemClick(User user) {
        Intent intent = new Intent(Dashboard.this, Booking.class);

        Parcelable userParcel = Parcels.wrap(user);
        Bundle bundle = new Bundle();
        bundle.putParcelable(AppsConstant.KEY_USER, userParcel);

        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void onUsersDataAvailable(List<User> userList) {
        UserListAdapter adapter = new UserListAdapter(this, userList, presenter);
        rvUser.setAdapter(adapter);
    }

    @Override
    public void onUsersDataNotAvailable(String message) {

    }

    @Override
    public void goToLoginScreen() {
        startActivity(new Intent(Dashboard.this, Login.class));
    }

}
