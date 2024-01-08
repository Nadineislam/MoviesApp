package com.example.movieapp.movie_home_feature.domain.repository

import com.example.movieapp.movie_home_feature.data.remote.dto.CategoriesResponse
import com.example.movieapp.movie_home_feature.data.remote.dto.TrendingTvResponse
import retrofit2.Response

interface TvRepository {
    suspend fun getTvCategoriesList(): Response<CategoriesResponse>
    suspend fun getTvCategoryList(categoryId: Int): Response<TrendingTvResponse>
}