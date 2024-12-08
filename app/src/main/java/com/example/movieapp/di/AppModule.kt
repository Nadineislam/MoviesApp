package com.example.movieapp.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import com.example.data.remote.ApiKeyInterceptor
import com.example.movieapp.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Singleton
    @Provides
    fun providesApiKeyInterceptor(): ApiKeyInterceptor {
        val apiKey = BuildConfig.API_KEY
        return ApiKeyInterceptor(apiKey)
    }

    @Singleton
    @Provides
    fun providesOkHttpClient(chuckerInterceptor: ChuckerInterceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor, apiKeyInterceptor: ApiKeyInterceptor
    ): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(chuckerInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(apiKeyInterceptor)
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): com.example.data.remote.MoviesApi = retrofit.create(
        com.example.data.remote.MoviesApi::class.java)

    @Provides
    @Singleton
    fun provideChuckerInterceptor(@ApplicationContext context: Context, chuckerCollector: ChuckerCollector): ChuckerInterceptor {
        return ChuckerInterceptor.Builder(context)
            // The previously created Collector
            .collector(chuckerCollector)
            // The max body content length in bytes, after this responses will be truncated.
            .maxContentLength(250_000L)
            // List of headers to replace with ** in the Chucker UI
            .redactHeaders("Auth-Token", "Bearer")
            // Read the whole response body even when the client does not consume the response completely.
            // This is useful in case of parsing errors or when the response body
            // is closed before being read like in Retrofit with Void and Unit types.
            .build()

    }
    @Provides
    @Singleton
    fun provideChuckerCollector(@ApplicationContext context: Context): ChuckerCollector {
        return ChuckerCollector(
            context = context,
            // Toggles visibility of the notification
            showNotification = true,
            // Allows to customize the retention period of collected data
            retentionPeriod = RetentionManager.Period.ONE_HOUR
        )
    }
}