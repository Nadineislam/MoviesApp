package com.example.domain.use_case

import com.example.domain.models.CategoriesResponse
import com.example.domain.repository.MoviesRepository
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesCategoriesUseCase @Inject constructor(private val repository: MoviesRepository) {
    suspend operator fun invoke(): Response<CategoriesResponse> =
        repository.getMoviesCategoriesList()


}