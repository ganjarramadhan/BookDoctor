package com.ganjarramadhan.bookdoctor.module.userprofile.view;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.ganjarramadhan.bookdoctor.R;
import com.ganjarramadhan.bookdoctor.module.userprofile.presenter.UserProfilePresenter;
import com.ganjarramadhan.bookdoctor.pojo.User;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserProfile extends AppCompatActivity implements UserProfileInterface {

    @Bind(R.id.activity_user_profile_toolbar) Toolbar toolbar;
    @Bind(R.id.activity_user_profile_coordinator_layout) CoordinatorLayout coordinatorLayout;

    @Bind(R.id.activity_user_profile_civ_avatar) ImageView imgView;
    @Bind(R.id.activity_user_profile_et_full_name) EditText etFullName;
    @Bind(R.id.activity_user_profile_et_password) EditText etPassword;
    @Bind(R.id.activity_user_profile_et_password2) EditText etPassword2;
    @Bind(R.id.activity_user_profile_btn_save) AppCompatButton btnSave;

    UserProfilePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // load layout
        setContentView(R.layout.activity_user_profile);

        // butter knife inject
        ButterKnife.bind(this);

        // set support action bar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // init presenter
        presenter = new UserProfilePresenter(this);

    }

    @OnClick(R.id.activity_user_profile_btn_save)
    public void onSaveButtonClick(View v){

    }

    @Override
    public void onSaveProfileSuccess(User user) {

    }

    @Override
    public void onSaveProfileFailed(String message) {

    }
}
