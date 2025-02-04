package com.example.movie_feature.domain.repository

import com.example.domain.result.OutCome
import com.example.movie_feature.domain.models.TrendingMoviesResponse
import com.example.movie_feature.domain.models.TrendingPeopleResponse
import com.example.movie_feature.domain.models.TrendingTvResponse

interface HomeRepository {
    suspend fun getTrendingMovies(): OutCome<TrendingMoviesResponse>
    suspend fun getTrendingTv(): OutCome<TrendingTvResponse>
    suspend fun getTrendingPeople(): OutCome<TrendingPeopleResponse>
    suspend fun getSearchMovie(movieName: String): OutCome<TrendingTvResponse>

}