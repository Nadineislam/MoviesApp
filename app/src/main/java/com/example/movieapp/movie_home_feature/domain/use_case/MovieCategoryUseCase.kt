package com.example.movieapp.movie_home_feature.domain.use_case

import com.example.movieapp.movie_home_feature.data.remote.dto.TrendingMoviesResponse
import com.example.movieapp.movie_home_feature.domain.repository.MoviesRepository
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieCategoryUseCase @Inject constructor(private val repository: MoviesRepository) {
    suspend operator fun invoke(page:Int,categoryId: Int): Response<TrendingMoviesResponse> =
        repository.getMovieCategoryList(page = page, categoryId = categoryId)
}