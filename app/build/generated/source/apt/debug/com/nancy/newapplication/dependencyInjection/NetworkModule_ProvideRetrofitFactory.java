// Generated by dagger.internal.codegen.ComponentProcessor (https://google.github.io/dagger).
package com.nancy.newapplication.dependencyInjection;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public final class NetworkModule_ProvideRetrofitFactory implements Factory<Retrofit> {
  private final NetworkModule module;

  private final Provider<GsonConverterFactory> gsonConverterFactoryProvider;

  private final Provider<RxJava2CallAdapterFactory> callAdapterFactoryProvider;

  public NetworkModule_ProvideRetrofitFactory(
      NetworkModule module,
      Provider<GsonConverterFactory> gsonConverterFactoryProvider,
      Provider<RxJava2CallAdapterFactory> callAdapterFactoryProvider) {
    this.module = module;
    this.gsonConverterFactoryProvider = gsonConverterFactoryProvider;
    this.callAdapterFactoryProvider = callAdapterFactoryProvider;
  }

  @Override
  public Retrofit get() {
    return Preconditions.checkNotNull(
        module.provideRetrofit(
            gsonConverterFactoryProvider.get(), callAdapterFactoryProvider.get()),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<Retrofit> create(
      NetworkModule module,
      Provider<GsonConverterFactory> gsonConverterFactoryProvider,
      Provider<RxJava2CallAdapterFactory> callAdapterFactoryProvider) {
    return new NetworkModule_ProvideRetrofitFactory(
        module, gsonConverterFactoryProvider, callAdapterFactoryProvider);
  }

  public static Retrofit proxyProvideRetrofit(
      NetworkModule instance,
      GsonConverterFactory gsonConverterFactory,
      RxJava2CallAdapterFactory callAdapterFactory) {
    return instance.provideRetrofit(gsonConverterFactory, callAdapterFactory);
  }
}
