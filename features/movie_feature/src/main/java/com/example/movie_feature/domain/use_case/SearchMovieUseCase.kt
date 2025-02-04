package com.example.movie_feature.domain.use_case

import com.example.domain.result.OutCome
import com.example.domain.usecase.AsyncUseCase
import com.example.movie_feature.domain.models.TrendingTvResponse
import com.example.movie_feature.domain.repository.HomeRepository
import javax.inject.Inject

open class SearchMovieUseCase @Inject constructor(private val repository: HomeRepository) :
    AsyncUseCase<TrendingTvResponse>() {
    override suspend fun run(
        param: String?,
        page: Int?,
        categoryId: Int?
    ): OutCome<TrendingTvResponse> =
        repository.getSearchMovie(param.toString())

}
