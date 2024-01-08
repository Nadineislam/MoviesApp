package com.example.movieapp.movie_home_feature.data.repository

import com.example.movieapp.movie_home_feature.data.remote.MoviesApi
import com.example.movieapp.movie_home_feature.data.remote.dto.CategoriesResponse
import com.example.movieapp.movie_home_feature.data.remote.dto.TrendingTvResponse
import com.example.movieapp.movie_home_feature.domain.repository.TvRepository
import retrofit2.Response
import javax.inject.Inject

class TvRepositoryImpl @Inject constructor(private val moviesApi: MoviesApi) : TvRepository {
    override suspend fun getTvCategoriesList(): Response<CategoriesResponse> =
        moviesApi.getTvCategoriesList()

    override suspend fun getTvCategoryList(categoryId: Int): Response<TrendingTvResponse> =
        moviesApi.getTvCategoryList(categoryId = categoryId)
}