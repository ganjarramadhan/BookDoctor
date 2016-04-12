package com.ganjarramadhan.bookdoctor.module.splashscreen.presenter;

import android.os.Handler;
import android.os.Message;

import com.ganjarramadhan.bookdoctor.module.splashscreen.view.SplashScreenInterface;

/**
 * Created by ganjarramadhan on 4/9/16.
 */
public class SplashScreenPresenter implements SplashScreenPresenterInterface{

    public final static int MSG_CONTINUE = 1234;

    private final static long DELAY = 3000;
    private CustomHandler customHandler;

    private SplashScreenInterface view;

    public SplashScreenPresenter(SplashScreenInterface view){
        this.view = view;
    }

    @Override
    public void doSplash(){
        // init custom handler for handling interrupt action when loading splash
        customHandler = new CustomHandler();

        // set delayed message
        customHandler.sendEmptyMessageDelayed(MSG_CONTINUE, DELAY);
    }

    @Override
    public void destroySplash() {
        customHandler.removeMessages(MSG_CONTINUE);
    }

    // inner class for custom handler
    class CustomHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_CONTINUE:
                    _continue();
                    break;
            }
        }

        private void _continue() {
            // go to dashboard
            view.goToDashboard();
        }



    }

}
