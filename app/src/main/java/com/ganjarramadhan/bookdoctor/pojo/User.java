package com.ganjarramadhan.bookdoctor.pojo;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * Created by ganjarramadhan on 4/9/16.
 */

@Parcel
public class User {

    public static final String TYPE_DOCTOR = "doctor";
    public static final String TYPE_CUSTOMER = "customer";

    @SerializedName("id")
    int id;

    @SerializedName("email")
    String email;

    String password;

    @SerializedName("full_name")
    String fullName;

    @SerializedName("avatar_url")
    String avatarUrl;

    @SerializedName("type")
    String type;

    public User() {
    }

    public User(int id, String email, String fullName, String avatarUrl, String type) {
        this.id = id;
        this.email = email;
        this.fullName = fullName;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
