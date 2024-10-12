package com.example.domain.use_case

import com.example.domain.models.TrendingTvResponse
import com.example.domain.repository.TvRepository
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TvCategoryUseCase @Inject constructor(private val repository: TvRepository) {
    suspend operator fun invoke(page: Int, categoryId: Int): Response<TrendingTvResponse> =
        repository.getTvCategoryList(page = page, categoryId = categoryId)
}