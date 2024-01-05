package com.example.movieapp.movie_home_feature.data.repository

import com.example.movieapp.movie_home_feature.data.remote.MoviesApi
import com.example.movieapp.movie_home_feature.data.remote.dto.TrendingMoviesResponse
import com.example.movieapp.movie_home_feature.data.remote.dto.TrendingPeopleResponse
import com.example.movieapp.movie_home_feature.data.remote.dto.TrendingTvResponse
import com.example.movieapp.movie_home_feature.domain.repository.MoviesRepository
import retrofit2.Response

class MoviesRepositoryImpl(private val moviesApi: MoviesApi) : MoviesRepository {
    override suspend fun getTrendingMovies(): Response<TrendingMoviesResponse> =
        moviesApi.getTrendingMovies()

    override suspend fun getTrendingTv(): Response<TrendingTvResponse> =
        moviesApi.getTrendingTv()

    override suspend fun getTrendingPeople(): Response<TrendingPeopleResponse> =
        moviesApi.getTrendingPeople()

    override suspend fun getSearchMovie(movieName: String): Response<TrendingTvResponse> =
        moviesApi.getSearchedMovie(movieName)


}