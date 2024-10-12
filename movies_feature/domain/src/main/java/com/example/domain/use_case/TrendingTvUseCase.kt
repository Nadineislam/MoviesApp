package com.example.domain.use_case

import com.example.domain.models.TrendingTvResponse
import com.example.domain.repository.HomeRepository
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TrendingTvUseCase @Inject constructor(private val repository: HomeRepository) {
    suspend operator fun invoke(): Response<TrendingTvResponse> =
        repository.getTrendingTv()


}