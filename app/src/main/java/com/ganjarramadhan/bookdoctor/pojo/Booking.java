package com.ganjarramadhan.bookdoctor.pojo;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.Date;

/**
 * Created by ganjarramadhan on 4/11/16.
 */
@Parcel
public class Booking {

    @SerializedName("id")
    int id;

    @SerializedName("patient")
    User customer;

    @SerializedName("doctor")
    User doctor;

    @SerializedName("date")
    Date bookingDate;

    @SerializedName("time_index")
    int bookingTime;

    @SerializedName("status")
    String status;

    @SerializedName("created_on")
    Date createdOn;

    @SerializedName("updated_on")
    Date updatedOn;

    public Booking() {
    }

    public Booking(int id, User customer, User doctor, Date bookingDate, int bookingTime) {
        this.id = id;
        this.customer = customer;
        this.doctor = doctor;
        this.bookingDate = bookingDate;
        this.bookingTime = bookingTime;
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

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public int getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(int bookingTime) {
        this.bookingTime = bookingTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }
}
