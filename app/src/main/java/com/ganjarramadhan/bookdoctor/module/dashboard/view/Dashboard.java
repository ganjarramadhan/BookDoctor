package com.ganjarramadhan.bookdoctor.module.dashboard.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.ganjarramadhan.bookdoctor.R;
import com.ganjarramadhan.bookdoctor.module.booking.view.Booking;
import com.ganjarramadhan.bookdoctor.module.dashboard.adapter.DoctorListAdapter;
import com.ganjarramadhan.bookdoctor.module.dashboard.adapter.DoctorsBookingListAdapter;
import com.ganjarramadhan.bookdoctor.module.dashboard.presenter.DashboardPresenter;
import com.ganjarramadhan.bookdoctor.module.history.view.History;
import com.ganjarramadhan.bookdoctor.module.login.view.Login;
import com.ganjarramadhan.bookdoctor.module.userprofile.view.UserProfile;
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

    @Bind(R.id.layout_content_dashboard) View viewContent;
    @Bind(R.id.layout_loading_dashboard) View viewLoading;

    @Bind(R.id.adapter_user_list_tv_label) TextView labelHeader;

    DashboardPresenter presenter;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // load ui
        setContentView(R.layout.activity_dashboard);

        // inject butter knife
        ButterKnife.bind(this);

        // set action bar
        setSupportActionBar(toolbar);

        // hide main content
        hideMainContent();

        // init presenter
        presenter = new DashboardPresenter(this);

        // get logged in user
        this.user = presenter.getLoggedInUser(this);
        labelHeader.setText(getLabelHeader(user));

        // invalidate menu
        invalidateOptionsMenu();
        supportInvalidateOptionsMenu();

        // load data
        presenter.loadUsers(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        if (user != null){
            if (user.getType() == User.GROUP_CUSTOMER){
                menuInflater.inflate(R.menu.patient_menu, menu);
            } else if (user.getType() == User.GROUP_DOCTOR){
                menuInflater.inflate(R.menu.doctor_menu, menu);
            } else {
                menuInflater.inflate(R.menu.guest_menu, menu);
            }
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.guest_menu_login:
                presenter.goToLogin();
                break;
            case R.id.patient_menu_profile:
                goToProfile();
                break;
            case R.id.patient_menu_history:
                goToHistory();
                break;
            case R.id.patient_menu_logout:
                presenter.userLogout(this);
                break;
            case R.id.doctor_menu_profile:
                goToProfile();
                break;
            case R.id.doctor_menu_logout:
                presenter.userLogout(this);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
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
        DoctorListAdapter adapter = new DoctorListAdapter(this, userList, presenter);
        rvUser.setAdapter(adapter);
        showMainContent();
    }

    @Override
    public void onUsersDataNotAvailable(String message) {
        showMainContent();
        AlertDialog.Builder builderAlert = new AlertDialog.Builder(this, R.style.DialogTheme);
        builderAlert.setMessage(message);
        builderAlert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builderAlert.show();
    }

    @Override
    public void onDoctorScheduleAvailable(List<com.ganjarramadhan.bookdoctor.pojo.Booking> bookingList) {
        labelHeader.setText(getLabelHeader(user));
        showMainContent();
        if (bookingList.size() > 0){
            DoctorsBookingListAdapter adapter = new DoctorsBookingListAdapter(this, bookingList, presenter);
            rvUser.setAdapter(adapter);
        } else {
            onUsersDataNotAvailable(getString(R.string.doctor_dont_have_booking_history_message));
        }
    }

    @Override
    public void onDoctorScheduleNotAvailable(String message) {
        labelHeader.setText(getLabelHeader(user));
        onUsersDataNotAvailable(message);
    }

    @Override
    public void goToLoginScreen() {
        startActivity(new Intent(Dashboard.this, Login.class));
    }

    @Override
    public void onUserLogout() {
        // get logged in user
        this.user = presenter.getLoggedInUser(this);

        // invalidate menu
        invalidateOptionsMenu();
        supportInvalidateOptionsMenu();

        Snackbar.make(coordinatorLayout, getString(R.string.logout_success), Snackbar.LENGTH_LONG).show();

        // reload data
        labelHeader.setText(getLabelHeader(user));
        hideMainContent();
        presenter.loadUsers(this);

    }

    @Override
    public void goToHistory() {
        startActivity(new Intent(Dashboard.this, History.class));
    }

    @Override
    public void goToProfile() {
        startActivity(new Intent(Dashboard.this, UserProfile.class));
    }

    private void showMainContent(){
        viewLoading.setVisibility(View.GONE);
        viewContent.setVisibility(View.VISIBLE);
    }

    private void hideMainContent(){
        viewContent.setVisibility(View.GONE);
        viewLoading.setVisibility(View.VISIBLE);
    }

    private String getLabelHeader(User user){

        if (user != null){
            if (user.getType() == User.GROUP_DOCTOR){
                return "Patients List. You are logged in as " + user.getFirstName();
            } else if (user.getType() == User.GROUP_CUSTOMER) {
                return "Doctors List. You are logged in as " + user.getFirstName();
            }
        }

        return "Doctors List";
    }

}
