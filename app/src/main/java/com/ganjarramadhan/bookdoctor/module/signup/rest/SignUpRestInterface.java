package com.ganjarramadhan.bookdoctor.module.signup.rest;

import com.ganjarramadhan.bookdoctor.pojo.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by ganjarramadhan on 4/9/16.
 */
public interface SignUpRestInterface {

    @FormUrlEncoded
    @POST(SignUpAPIURLConstant.REGISTER_URL)
    Call<User> register(@Field("email") String email, @Field("password") String password,
                        @Field("first_name") String firstName, @Field("last_name") String lastName);

}
