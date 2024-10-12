package com.example.domain.use_case

import com.example.domain.models.TrendingPeopleResponse
import com.example.domain.repository.HomeRepository
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TrendingPeopleUseCase @Inject constructor(private val repository: HomeRepository) {
    suspend operator fun invoke(): Response<TrendingPeopleResponse> =
        repository.getTrendingPeople()

}