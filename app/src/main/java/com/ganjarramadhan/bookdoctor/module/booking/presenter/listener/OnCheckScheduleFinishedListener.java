package com.ganjarramadhan.bookdoctor.module.booking.presenter.listener;

import java.util.List;

/**
 * Created by ganjarramadhan on 4/13/16.
 */
public interface OnCheckScheduleFinishedListener {

    void onCheckScheduleSuccess(List<Integer> listUnAvailableDayIndex);
    void onCheckScheduleFailed(String message);

}
