package com.ganjarramadhan.bookdoctor.module.history.adapter.model;


import com.ganjarramadhan.bookdoctor.pojo.Booking;

/**
 * Created by ganjarramadhan on 4/11/16.
 */
public class HistoryListAdapterModel {

    private Booking bookingData;
    private HistoryListHeaderModelAdapter headerData;
    private int listType;

    public HistoryListAdapterModel(Booking bookingData, HistoryListHeaderModelAdapter headerData, int listType) {
        this.bookingData = bookingData;
        this.headerData = headerData;
        this.listType = listType;
    }

    public Booking getBookingData() {
        return bookingData;
    }

    public void setBookingData(Booking bookingData) {
        this.bookingData = bookingData;
    }

    public HistoryListHeaderModelAdapter getHeaderData() {
        return headerData;
    }

    public void setHeaderData(HistoryListHeaderModelAdapter headerData) {
        this.headerData = headerData;
    }

    public int getListType() {
        return listType;
    }

    public void setListType(int listType) {
        this.listType = listType;
    }
}
