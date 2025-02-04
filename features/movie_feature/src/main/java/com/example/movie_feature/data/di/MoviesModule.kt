package com.example.movie_feature.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import com.example.data.factory.ServiceFactory
import com.example.data.source.NetworkDataSource
import com.example.movie_feature.data.remote.MoviesService
import com.example.movie_feature.domain.repository.HomeRepository
import com.example.movie_feature.domain.repository.MoviesRepository
import com.example.movie_feature.domain.repository.TvRepository
import com.example.movie_feature.domain.use_case.MovieCategoryUseCase
import com.example.movie_feature.domain.use_case.MoviesCategoriesUseCase
import com.example.movie_feature.domain.use_case.SearchMovieUseCase
import com.example.movie_feature.domain.use_case.TrendingMoviesUseCase
import com.example.movie_feature.domain.use_case.TrendingPeopleUseCase
import com.example.movie_feature.domain.use_case.TrendingTvUseCase
import com.example.movie_feature.domain.use_case.TvCategoriesUseCase
import com.example.movie_feature.domain.use_case.TvCategoryUseCase
import com.google.gson.Gson
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MoviesModule {
    @Provides
    @Singleton
    fun provideMoviesServiceFactory(serviceFactory: ServiceFactory): MoviesService {
        return serviceFactory.create(MoviesService::class.java)
    }

    @Provides
    @Singleton
    fun provideTrendingMoviesUseCase(repository: HomeRepository): TrendingMoviesUseCase {
        return TrendingMoviesUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideTrendingPeopleUseCase(repository: HomeRepository): TrendingPeopleUseCase {
        return TrendingPeopleUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideTrendingTvUseCase(repository: HomeRepository): TrendingTvUseCase {
        return TrendingTvUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideSearchMovieUseCase(repository: HomeRepository): SearchMovieUseCase {
        return SearchMovieUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideTvCategoriesUseCase(repository: TvRepository): TvCategoriesUseCase {
        return TvCategoriesUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideMoviesCategoriesUseCase(repository: MoviesRepository): MoviesCategoriesUseCase {
        return MoviesCategoriesUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideMovieCategoryUseCase(repository: MoviesRepository): MovieCategoryUseCase {
        return MovieCategoryUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideTvCategoryUseCase(repository: TvRepository): TvCategoryUseCase {
        return TvCategoryUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideNetworkDataSource(
        moviesService: MoviesService,
        gson: Gson
    ): NetworkDataSource<MoviesService> {
        return NetworkDataSource(moviesService, gson)
    }
}