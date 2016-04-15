package com.ganjarramadhan.bookdoctor.module.history.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ganjarramadhan on 4/9/16.
 */


public class HistoryRestClient {

    private static HistoryRestInterface REST_CLIENT;

    static {
        setupRestClient();
    }

    private HistoryRestClient() {
    }

    public static HistoryRestInterface get() {
        return REST_CLIENT;
    }

    private static void setupRestClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);  // <-- this is the important line!

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(HistoryAPIURLConstant.BASE_HISTORY_URL)
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create(gson));

        Retrofit restAdapter = builder.build();
        REST_CLIENT = restAdapter.create(HistoryRestInterface.class);

    }

}
