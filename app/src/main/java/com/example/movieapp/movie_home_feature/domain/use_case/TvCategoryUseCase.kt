package com.example.movieapp.movie_home_feature.domain.use_case

import com.example.movieapp.movie_home_feature.data.remote.dto.TrendingTvResponse
import com.example.movieapp.movie_home_feature.domain.repository.TvRepository
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TvCategoryUseCase @Inject constructor(private val repository: TvRepository) {
    suspend operator fun invoke(page:Int,categoryId:Int): Response<TrendingTvResponse> =
        repository.getTvCategoryList(page=page,categoryId = categoryId)
}