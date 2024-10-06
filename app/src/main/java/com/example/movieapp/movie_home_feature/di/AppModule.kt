package com.example.movieapp.movie_home_feature.di

import com.example.movieapp.BuildConfig
import com.example.movieapp.movie_home_feature.data.remote.MoviesApi
import com.example.movieapp.movie_home_feature.data.repository.HomeRepositoryImpl
import com.example.movieapp.movie_home_feature.data.repository.MoviesRepositoryImpl
import com.example.movieapp.movie_home_feature.data.repository.TvRepositoryImpl
import com.example.movieapp.movie_home_feature.domain.repository.HomeRepository
import com.example.movieapp.movie_home_feature.domain.repository.MoviesRepository
import com.example.movieapp.movie_home_feature.domain.repository.TvRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesHomeRepository(api: MoviesApi): HomeRepository {
        return HomeRepositoryImpl(api)
    }
    @Provides
    @Singleton
    fun providesMoviesRepository(api: MoviesApi): MoviesRepository {
        return MoviesRepositoryImpl(api)
    }
    @Provides
    @Singleton
    fun providesTvRepository(api: MoviesApi): TvRepository {
        return TvRepositoryImpl(api)
    }

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Singleton
    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
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
    fun provideApiService(retrofit: Retrofit): MoviesApi = retrofit.create(MoviesApi::class.java)
}