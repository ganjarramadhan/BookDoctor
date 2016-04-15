package com.ganjarramadhan.bookdoctor.module.signup.rest;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ganjarramadhan on 4/9/16.
 */

public class SignUpRestClient {

    private static SignUpRestInterface REST_CLIENT;

    static {
        setupRestClient();
    }

    private SignUpRestClient() {
    }

    public static SignUpRestInterface get() {
        return REST_CLIENT;
    }

    private static void setupRestClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);  // <-- this is the important line!

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(SignUpAPIURLConstant.BASE_API_USER_URL)
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit restAdapter = builder.build();
        REST_CLIENT = restAdapter.create(SignUpRestInterface.class);

    }

}
