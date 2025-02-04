package com.example.movie_feature.data.repository

import com.example.data.mapper.toDomain
import com.example.data.source.NetworkDataSource
import com.example.domain.result.OutCome
import com.example.movie_feature.data.remote.MoviesService
import com.example.movie_feature.domain.models.TrendingMoviesResponse
import com.example.movie_feature.domain.models.TrendingPeopleResponse
import com.example.movie_feature.domain.models.TrendingTvResponse
import com.example.movie_feature.domain.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val networkDataSource: NetworkDataSource<MoviesService>
) :
    HomeRepository {
    override suspend fun getTrendingMovies(): OutCome<TrendingMoviesResponse> {
        return networkDataSource.performRequest(
            request = { getTrendingMovies() },
            onSuccess = { response, _ -> OutCome.success(response) },
            onError = { errorResponse, code -> OutCome.error(errorResponse.toDomain(code)) }
        )
    }

    override suspend fun getTrendingTv(): OutCome<TrendingTvResponse> {
        return networkDataSource.performRequest(
            request = { getTrendingTv() },
            onSuccess = { response, _ -> OutCome.success(response) },
            onError = { errorResponse, code -> OutCome.error(errorResponse.toDomain(code)) }
        )
    }

    override suspend fun getTrendingPeople(): OutCome<TrendingPeopleResponse> {
        return networkDataSource.performRequest(
            request = { getTrendingPeople() },
            onSuccess = { response, _ -> OutCome.success(response) },
            onError = { errorResponse, code -> OutCome.error(errorResponse.toDomain(code)) }
        )
    }

    override suspend fun getSearchMovie(movieName: String): OutCome<TrendingTvResponse> {
        return networkDataSource.performRequest(
            request = { getSearchedMovie(movieName) },
            onSuccess = { response, _ -> OutCome.success(response) },
            onEmpty = { OutCome.empty() },
            onError = { errorResponse, code -> OutCome.error(errorResponse.toDomain(code)) }
        )
    }

}