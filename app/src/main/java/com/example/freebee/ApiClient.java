package com.example.freebee;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiClient {
 

    public static final String BASE_URL = "http://51.68.109.219:9294";
    private static Retrofit retrofit = null;

 
    public static ApiInterface getInstance() {

          if (retrofit==null) {
              Gson gson = new GsonBuilder()
                      .setLenient()
                      .create();
              retrofit = new Retrofit.Builder()
                      .baseUrl(BASE_URL)
                      .addConverterFactory(GsonConverterFactory.create(gson))
                      .client(getRequestHeader())
                      .build();
          }

        return retrofit.create(ApiInterface.class);
    }

    private static OkHttpClient getRequestHeader() {
        HttpLoggingInterceptor interceptor=new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient httpClient = new OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .connectTimeout(40,TimeUnit.SECONDS)
        .readTimeout(30,TimeUnit.SECONDS)
        .writeTimeout(20, TimeUnit.SECONDS)
        .build();
        return httpClient;
    }

}