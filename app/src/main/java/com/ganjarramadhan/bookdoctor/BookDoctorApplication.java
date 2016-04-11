package com.ganjarramadhan.bookdoctor;

import android.app.Application;
import android.util.Log;

import timber.log.Timber;

/**
 * Created by ganjarramadhan on 4/9/16.
 */
public class BookDoctorApplication extends Application {

    private static BookDoctorApplication myApp;

    public BookDoctorApplication getInstance(){

        return myApp;
    }
    @Override
    public void onCreate() {
        super.onCreate();

        myApp = this;

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

    }
}
