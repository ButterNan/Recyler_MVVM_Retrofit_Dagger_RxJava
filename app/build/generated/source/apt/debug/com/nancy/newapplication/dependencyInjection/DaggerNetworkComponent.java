// Generated by dagger.internal.codegen.ComponentProcessor (https://google.github.io/dagger).
package com.nancy.newapplication.dependencyInjection;

import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public final class DaggerNetworkComponent implements NetworkComponent {
  private Provider<GsonConverterFactory> provideGsonConverterFactoryProvider;

  private Provider<RxJava2CallAdapterFactory> provideRxJavaCallAdapterFactoryProvider;

  private Provider<Retrofit> provideRetrofitProvider;

  private DaggerNetworkComponent(Builder builder) {
    initialize(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  @SuppressWarnings("unchecked")
  private void initialize(final Builder builder) {
    this.provideGsonConverterFactoryProvider =
        DoubleCheck.provider(
            NetworkModule_ProvideGsonConverterFactoryFactory.create(builder.networkModule));
    this.provideRxJavaCallAdapterFactoryProvider =
        DoubleCheck.provider(
            NetworkModule_ProvideRxJavaCallAdapterFactoryFactory.create(builder.networkModule));
    this.provideRetrofitProvider =
        DoubleCheck.provider(
            NetworkModule_ProvideRetrofitFactory.create(
                builder.networkModule,
                provideGsonConverterFactoryProvider,
                provideRxJavaCallAdapterFactoryProvider));
  }

  @Override
  public Retrofit retrofit() {
    return provideRetrofitProvider.get();
  }

  public static final class Builder {
    private NetworkModule networkModule;

    private Builder() {}

    public NetworkComponent build() {
      if (networkModule == null) {
        throw new IllegalStateException(NetworkModule.class.getCanonicalName() + " must be set");
      }
      return new DaggerNetworkComponent(this);
    }

    public Builder networkModule(NetworkModule networkModule) {
      this.networkModule = Preconditions.checkNotNull(networkModule);
      return this;
    }
  }
}
