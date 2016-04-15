package com.ganjarramadhan.bookdoctor.module.signup.model;

import com.ganjarramadhan.bookdoctor.module.signup.presenter.listener.OnRegisteringUserFinishedListener;
import com.ganjarramadhan.bookdoctor.module.signup.rest.SignUpRestClient;
import com.ganjarramadhan.bookdoctor.pojo.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ganjarramadhan on 4/9/16.
 */
public class SignUpModel implements SignUpModelInterface {

    @Override
    public void registeringUserToServer(final User user, final OnRegisteringUserFinishedListener listener) {

        Call<User> register = SignUpRestClient.get().register(user.getEmail(), user.getPassword(),
                user.getFirstName(), user.getLastName());

        register.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()){
                    listener.onRegisterSuccess(response.body());
                } else {
                    listener.onRegisterFailed(response.message());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                listener.onRegisterFailed(t.getMessage());
            }
        });

    }
}
