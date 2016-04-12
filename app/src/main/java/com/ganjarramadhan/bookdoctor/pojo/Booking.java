package com.ganjarramadhan.bookdoctor.pojo;

/**
 * Created by ganjarramadhan on 4/11/16.
 */
public class Booking {

    int id;
    User customer;
    User doctor;
    long bookingDate;
    String bookingTime;
    boolean isCanceled;

    public Booking() {
    }

    public Booking(int id, User customer, User doctor, long bookingDate, String bookingTime, boolean isCanceled) {
        this.id = id;
        this.customer = customer;
        this.doctor = doctor;
        this.bookingDate = bookingDate;
        this.bookingTime = bookingTime;
        this.isCanceled = isCanceled;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public User getDoctor() {
        return doctor;
    }

    public void setDoctor(User doctor) {
        doctor = doctor;
    }

    public long getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(long bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(String bookingTime) {
        this.bookingTime = bookingTime;
    }

    public boolean isCanceled() {
        return isCanceled;
    }

    public void setCanceled(boolean canceled) {
        isCanceled = canceled;
    }
}
