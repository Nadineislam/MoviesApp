package com.example.movieapp.movie_home_feature.domain.repository

import com.example.movieapp.movie_home_feature.data.remote.dto.TrendingMoviesResponse
import com.example.movieapp.movie_home_feature.data.remote.dto.TrendingPeopleResponse
import com.example.movieapp.movie_home_feature.data.remote.dto.TrendingTvResponse
import retrofit2.Response

interface MoviesRepository {
    suspend fun getTrendingMovies(): Response<TrendingMoviesResponse>
    suspend fun getTrendingTv(): Response<TrendingTvResponse>
    suspend fun getTrendingPeople(): Response<TrendingPeopleResponse>
    suspend fun getSearchMovie(movieName: String): Response<TrendingTvResponse>

}