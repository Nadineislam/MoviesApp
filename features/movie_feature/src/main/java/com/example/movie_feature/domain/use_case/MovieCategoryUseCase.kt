package com.example.movie_feature.domain.use_case

import com.example.domain.result.OutCome
import com.example.domain.usecase.AsyncUseCase
import com.example.movie_feature.domain.models.TrendingMoviesResponse
import com.example.movie_feature.domain.repository.MoviesRepository
import javax.inject.Inject

class MovieCategoryUseCase @Inject constructor(private val repository: MoviesRepository) :
    AsyncUseCase<TrendingMoviesResponse>() {
    override suspend fun run(
        param: String?,
        page: Int?,
        categoryId: Int?
    ): OutCome<TrendingMoviesResponse> =
        repository.getMovieCategoryList(page = page ?: 0, categoryId = categoryId ?: 0)

}