package com.example.movie_feature.domain.use_case

import com.example.domain.result.OutCome
import com.example.domain.usecase.AsyncUseCase
import com.example.movie_feature.domain.models.TrendingMoviesResponse
import com.example.movie_feature.domain.repository.HomeRepository
import javax.inject.Inject

class TrendingMoviesUseCase @Inject constructor(private val repository: HomeRepository) :
    AsyncUseCase<TrendingMoviesResponse>() {
    override suspend fun run(
        param: String?,
        page: Int?,
        categoryId: Int?
    ): OutCome<TrendingMoviesResponse> =
        repository.getTrendingMovies()

}