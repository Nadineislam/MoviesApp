package com.example.data.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import com.example.core.BuildConfig
import com.example.data.connectivity.NetworkMonitorInterface
import com.example.data.constants.CHUCKER_INTERCEPTOR_TAG
import com.example.data.constants.CONNECTIVITY_INTERCEPTOR_TAG
import com.example.data.constants.LOGGING_INTERCEPTOR_TAG
import com.example.data.interceptor.ConnectivityInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class InterceptorsModule {

    @Provides
    @Singleton
    @Named(CONNECTIVITY_INTERCEPTOR_TAG)
    fun provideConnectivityInterceptor(
        networkMonitorInterface: NetworkMonitorInterface,
    ): Interceptor {
        return ConnectivityInterceptor(
            networkMonitorInterface,
        )
    }

    @Provides
    @Singleton
    @Named(CHUCKER_INTERCEPTOR_TAG)
    fun provideChuckerInterceptor(@ApplicationContext context: Context): Interceptor {
        return ChuckerInterceptor.Builder(context)
            // The previously created Collector
            .collector(
                ChuckerCollector(
                    context = context,
                    showNotification = true,
                    retentionPeriod = RetentionManager.Period.ONE_HOUR,
                ),
            )
            // The max body content length in bytes, after this responses will be truncated.
            .maxContentLength(250_000L)
            // List of headers to replace with ** in the Chucker UI
           .redactHeaders("Auth-Token", "Bearer")
            // Read the whole response body even when the client does not consume the response completely.
            // This is useful in case of parsing errors or when the response body
            // is closed before being read like in Retrofit with Void and Unit types.
            .alwaysReadResponseBody(true)
            // Use decoder when processing request and response bodies. When multiple decoders are installed they
            // are applied in an order they were added.
            // Controls Android shortcut creation.
            .createShortcut(true)
            .build()
    }

    // Http Logging Interceptor
    @Provides
    @Singleton
    @Named(LOGGING_INTERCEPTOR_TAG)
    fun provideOkHttpLoggingInterceptor(): Interceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }

        return interceptor
    }
}