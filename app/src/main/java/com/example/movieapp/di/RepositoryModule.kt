package com.example.movieapp.di

import com.example.data.repository.HomeRepositoryImpl
import com.example.data.repository.MoviesRepositoryImpl
import com.example.data.repository.TvRepositoryImpl
import com.example.domain.repository.HomeRepository
import com.example.domain.repository.MoviesRepository
import com.example.domain.repository.TvRepository
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