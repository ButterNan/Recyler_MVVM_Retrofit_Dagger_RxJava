package com.nancy.newapplication.dependencyInjection;

import com.nancy.newapplication.Service.ApiService;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class ApiModule {

    @Provides
    @CustomScope
    ApiService provideFlowerService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }
}

