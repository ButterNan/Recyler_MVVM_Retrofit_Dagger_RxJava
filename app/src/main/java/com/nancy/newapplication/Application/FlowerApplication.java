package com.nancy.newapplication.Application;

import android.app.Application;

import com.nancy.newapplication.dependencyInjection.ApiComponent;
import com.nancy.newapplication.dependencyInjection.DaggerApiComponent;
import com.nancy.newapplication.dependencyInjection.DaggerNetworkComponent;
import com.nancy.newapplication.dependencyInjection.NetworkComponent;
import com.nancy.newapplication.dependencyInjection.NetworkModule;
import com.nancy.newapplication.utils.Constants;

public class FlowerApplication extends Application {

    ApiComponent mApiComponent;

    @Override
    public void onCreate() {
        resolveDependency();
        super.onCreate();
    }

    private void resolveDependency() {
        mApiComponent = DaggerApiComponent.builder().networkComponent(getNetworkComponent()).build();
    }

    public NetworkComponent getNetworkComponent() {
        return DaggerNetworkComponent.builder()
                .networkModule(new NetworkModule(Constants.BASE_URL))
                .build();
    }

    public ApiComponent getApiComponent() {
        return mApiComponent;
    }
}

