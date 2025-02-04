package com.example.data.di

//import com.example.data.remote.MoviesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
//import com.example.data.factory.ServiceFactory
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
//@Module
//@InstallIn(SingletonComponent::class)
//
//class Module {
//    @Provides
//    @Singleton
//    fun provideMoviesServiceFactory(serviceFactory: ServiceFactory): MoviesApi {
//        return serviceFactory.create(MoviesApi::class.java)
//    }
//}