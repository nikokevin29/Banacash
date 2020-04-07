package com.xbanana.banacash.API;

import java.util.concurrent.TimeUnit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Timeout;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static final String BASE_URL ="http://http://banacash.luxinoire.com/";
    public static Retrofit retrofit = null;
    public static Retrofit getClient(){
        if (retrofit == null) {
            OkHttpClient.Builder okhttpBuilder = new OkHttpClient().newBuilder();
            okhttpBuilder.connectTimeout(60, TimeUnit.SECONDS);
            okhttpBuilder.writeTimeout(60, TimeUnit.SECONDS);
            okhttpBuilder.readTimeout(60, TimeUnit.SECONDS);
            okhttpBuilder.retryOnConnectionFailure(true);
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okhttpBuilder.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
