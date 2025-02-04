package com.example.movie_feature.domain.use_case

import com.example.domain.result.OutCome
import com.example.domain.usecase.AsyncUseCase
import com.example.movie_feature.domain.models.TrendingPeopleResponse
import com.example.movie_feature.domain.repository.HomeRepository
import javax.inject.Inject

class TrendingPeopleUseCase @Inject constructor(private val repository: HomeRepository) :
    AsyncUseCase<TrendingPeopleResponse>() {
    override suspend fun run(
        param: String?,
        page: Int?,
        categoryId: Int?
    ): OutCome<TrendingPeopleResponse> =
        repository.getTrendingPeople()

}