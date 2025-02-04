package com.example.movie_feature.domain.use_case

import com.example.domain.result.OutCome
import com.example.domain.usecase.AsyncUseCase
import com.example.movie_feature.domain.models.TrendingTvResponse
import com.example.movie_feature.domain.repository.TvRepository
import javax.inject.Inject

class TvCategoryUseCase @Inject constructor(private val repository: TvRepository) :
    AsyncUseCase<TrendingTvResponse>() {
    override suspend fun run(
        param: String?,
        page: Int?,
        categoryId: Int?
    ): OutCome<TrendingTvResponse> =
        repository.getTvCategoryList(page = page ?: 0, categoryId = categoryId ?: 0)
}