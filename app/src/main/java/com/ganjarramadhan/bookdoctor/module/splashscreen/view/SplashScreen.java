package com.ganjarramadhan.bookdoctor.module.splashscreen.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ganjarramadhan.bookdoctor.R;
import com.ganjarramadhan.bookdoctor.module.dashboard.view.Dashboard;
import com.ganjarramadhan.bookdoctor.module.login.view.Login;
import com.ganjarramadhan.bookdoctor.module.splashscreen.presenter.SplashScreenPresenter;
import com.ganjarramadhan.bookdoctor.util.AppsUtil;

public class SplashScreen extends AppCompatActivity implements SplashScreenInterface {


    SplashScreenPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // set activity to full screen
        AppsUtil.setFullScreen(this);

        // load layout
        setContentView(R.layout.activity_splash_screen);

        // init presenter
        presenter = new SplashScreenPresenter(this);

        // init splash
        presenter.doSplash();

    }

    @Override
    protected void onDestroy() {
        presenter.destroySplash();
        super.onDestroy();
    }

    @Override
    public void goToDashboard() {
        startActivity(new Intent(SplashScreen.this, Dashboard.class));
        finish();
    }

}
