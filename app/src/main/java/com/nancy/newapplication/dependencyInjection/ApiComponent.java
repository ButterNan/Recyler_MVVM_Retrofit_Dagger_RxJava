package com.nancy.newapplication.dependencyInjection;


import com.nancy.newapplication.Activity.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@CustomScope
@Component(modules = ApiModule.class,dependencies = NetworkComponent.class)
public interface ApiComponent {

    void inject(MainActivity activity);
}
