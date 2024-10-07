package com.example.movieapp.movie_home_feature.di

import com.example.movieapp.movie_home_feature.data.repository.HomeRepositoryImpl
import com.example.movieapp.movie_home_feature.data.repository.MoviesRepositoryImpl
import com.example.movieapp.movie_home_feature.data.repository.TvRepositoryImpl
import com.example.movieapp.movie_home_feature.domain.repository.HomeRepository
import com.example.movieapp.movie_home_feature.domain.repository.MoviesRepository
import com.example.movieapp.movie_home_feature.domain.repository.TvRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindHomeRepository(
        homeRepositoryImpl : HomeRepositoryImpl
    ): HomeRepository

    @Binds
    @Singleton
    abstract fun bindMoviesRepository(
        moviesRepositoryImpl: MoviesRepositoryImpl
    ): MoviesRepository

    @Binds
    @Singleton
    abstract fun bindTvRepository(
        tvRepositoryImpl: TvRepositoryImpl
    ): TvRepository
}