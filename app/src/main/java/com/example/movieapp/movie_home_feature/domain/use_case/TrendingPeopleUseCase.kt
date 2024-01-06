package com.example.movieapp.movie_home_feature.domain.use_case

import com.example.movieapp.movie_home_feature.data.remote.dto.TrendingPeopleResponse
import com.example.movieapp.movie_home_feature.domain.repository.HomeRepository
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TrendingPeopleUseCase @Inject constructor(private val repository: HomeRepository) {
    suspend operator fun invoke(): Response<TrendingPeopleResponse> =
        repository.getTrendingPeople()

}