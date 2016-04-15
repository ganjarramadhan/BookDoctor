package com.ganjarramadhan.bookdoctor.module.dashboard.rest;

import com.ganjarramadhan.bookdoctor.util.AppsConstant;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ganjarramadhan on 4/9/16.
 */


public class DashboardRestClient {

    private static DashboardRestInterface REST_CLIENT;

    static {
        setupRestClient();
    }

    private DashboardRestClient() {
    }

    public static DashboardRestInterface get() {
        return REST_CLIENT;
    }

    private static void setupRestClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(AppsConstant.BASE_API_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson));

        Retrofit restAdapter = builder.build();
        REST_CLIENT = restAdapter.create(DashboardRestInterface.class);

    }

}
