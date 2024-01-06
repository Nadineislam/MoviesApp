package com.example.movieapp.movie_home_feature.domain.use_case

import com.example.movieapp.movie_home_feature.data.remote.dto.CategoriesResponse
import com.example.movieapp.movie_home_feature.domain.repository.MoviesRepository
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoriesUseCase @Inject constructor(private val repository: MoviesRepository) {
    suspend operator fun invoke(): Response<CategoriesResponse> =
        repository.getCategoriesList()


}