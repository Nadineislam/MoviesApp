package com.example.data.di

import android.content.Context
import com.example.core.BuildConfig
import com.example.data.connectivity.NetworkMonitorImplementer
import com.example.data.connectivity.NetworkMonitorInterface
import com.example.data.constants.CHUCKER_INTERCEPTOR_TAG
import com.example.data.constants.CONNECTIVITY_INTERCEPTOR_TAG
import com.example.data.constants.LOGGING_INTERCEPTOR_TAG
import com.example.data.factory.ServiceFactory
import com.example.data.interceptor.ApiKeyInterceptor
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

  @Provides
  @Singleton
  fun provideGson(): Gson {
    return Gson()
  }

  @Provides
  @Singleton
  fun provideNetworkMonitor(context: Context): NetworkMonitorInterface {
    return NetworkMonitorImplementer(context)
  }

  @Singleton
  @Provides
  fun providesApiKeyInterceptor(): ApiKeyInterceptor {
    val apiKey = BuildConfig.API_KEY
    return ApiKeyInterceptor(apiKey)
  }

  // ok http factory
  @Provides
  @Singleton
  fun provideOkHttpCallFactory(
    @Named(LOGGING_INTERCEPTOR_TAG) okHttpLoggingInterceptor: Interceptor,
    @Named(CHUCKER_INTERCEPTOR_TAG) chuckerInterceptor: Interceptor,
    @Named(CONNECTIVITY_INTERCEPTOR_TAG) connectivityInterceptor: Interceptor,
    apiKeyInterceptor: ApiKeyInterceptor): OkHttpClient {
    return   OkHttpClient
      .Builder()
      .addInterceptor(okHttpLoggingInterceptor)
      .addInterceptor(apiKeyInterceptor)
      .addInterceptor(connectivityInterceptor)
      .addInterceptor(chuckerInterceptor)
      .retryOnConnectionFailure(true)
      .followRedirects(false)
      .followSslRedirects(false)
      .connectTimeout(60, TimeUnit.SECONDS)
      .readTimeout(60, TimeUnit.SECONDS)
      .writeTimeout(60, TimeUnit.SECONDS)
      .build()
  }

  @Provides
  @Singleton
  fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    val builder = Retrofit.Builder()
      .baseUrl(BuildConfig.BASE_URL)
      .baseUrl("https://api.themoviedb.org/3/")
      .client(okHttpClient)
      .addConverterFactory(GsonConverterFactory.create())
      .addCallAdapterFactory(CoroutineCallAdapterFactory())
    return builder.build()
  }
    @Provides
    @Singleton
    fun provideServiceFactory(retrofit: Retrofit): ServiceFactory {
        return ServiceFactory(retrofit)
    }


}
