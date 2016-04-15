package com.ganjarramadhan.bookdoctor.module.login.rest;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ganjarramadhan on 4/9/16.
 */


public class LoginRestClient {

    private static LoginRestInterface REST_CLIENT;

    static {
        setupRestClient();
    }

    private LoginRestClient() {
    }

    public static LoginRestInterface get() {
        return REST_CLIENT;
    }

    private static void setupRestClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);  // <-- this is the important line!

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(LoginAPIURLConstant.BASE_API_LOGIN_URL)
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit restAdapter = builder.build();
        REST_CLIENT = restAdapter.create(LoginRestInterface.class);

    }

}
