package com.ganjarramadhan.bookdoctor.pojo;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.List;

/**
 * Created by ganjarramadhan on 4/9/16.
 */

@Parcel
public class User {

    public static final int GROUP_DOCTOR = 3;
    public static final int GROUP_CUSTOMER = 4;

    @SerializedName("user_id")
    int id;

    @SerializedName("email")
    String email;

    String password;

    @SerializedName("first_name")
    String firstName;

    @SerializedName("last_name")
    String lastName;

    @SerializedName("avatar")
    String avatarUrl;

    @SerializedName("group_id")
    int type;

    @SerializedName("schedule")
    List<Integer> listAvailableDays;

    public User() {
    }

    public User(int id, String email, String firstName, String lastName, String avatarUrl, int type) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.avatarUrl = avatarUrl;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<Integer> getListAvailableDays() {
        return listAvailableDays;
    }

    public void setListAvailableDays(List<Integer> listAvailableDays) {
        this.listAvailableDays = listAvailableDays;
    }
}
