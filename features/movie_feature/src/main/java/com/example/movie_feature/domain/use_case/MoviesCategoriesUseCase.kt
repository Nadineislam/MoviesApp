package com.example.movie_feature.domain.use_case

import com.example.domain.result.OutCome
import com.example.domain.usecase.AsyncUseCase
import com.example.movie_feature.domain.models.CategoriesResponse
import com.example.movie_feature.domain.repository.MoviesRepository
import javax.inject.Inject

class MoviesCategoriesUseCase @Inject constructor(private val repository: MoviesRepository) :
    AsyncUseCase<CategoriesResponse>() {
    override suspend fun run(
        param: String?,
        page: Int?,
        categoryId: Int?
    ): OutCome<CategoriesResponse> =
        repository.getMoviesCategoriesList()

}