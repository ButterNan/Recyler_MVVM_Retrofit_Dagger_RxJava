package com.nancy.newapplication.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nancy.newapplication.utils.Constants;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {


    public static OkHttpClient client()
    {
        return new OkHttpClient();
    }
    public static Retrofit getRetrofit()
    {
        Gson gson = new GsonBuilder().setLenient().create();
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client())
                .build();
    }

    public static ApiService getAPIService()
    {
        return getRetrofit().create(ApiService.class);
    }
}
