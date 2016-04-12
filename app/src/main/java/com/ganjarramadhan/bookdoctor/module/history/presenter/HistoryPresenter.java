package com.ganjarramadhan.bookdoctor.module.history.presenter;

import com.ganjarramadhan.bookdoctor.module.history.model.HistoryModel;
import com.ganjarramadhan.bookdoctor.module.history.view.HistoryInterface;
import com.ganjarramadhan.bookdoctor.pojo.User;

import java.lang.ref.WeakReference;

/**
 * Created by ganjarramadhan on 4/11/16.
 */
public class HistoryPresenter implements HistoryPresenterInterface {

    private WeakReference<HistoryInterface> view;
    private HistoryModel model;

    public HistoryPresenter(HistoryInterface view) {
        this.view = new WeakReference<HistoryInterface>(view);
        this.model = new HistoryModel();
    }

    @Override
    public void loadHistory(User user) {

    }
}
