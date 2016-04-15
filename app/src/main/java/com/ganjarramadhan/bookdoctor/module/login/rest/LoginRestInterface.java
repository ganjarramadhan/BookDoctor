package com.ganjarramadhan.bookdoctor.module.login.rest;

import com.ganjarramadhan.bookdoctor.pojo.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by ganjarramadhan on 4/9/16.
 */
public interface LoginRestInterface {

    @FormUrlEncoded
    @POST(LoginAPIURLConstant.LOGIN_URL)
    Call<User> doLogin(@Field("email") String email, @Field("password") String password);

}
