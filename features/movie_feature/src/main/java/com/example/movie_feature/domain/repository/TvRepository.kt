package com.example.movie_feature.domain.repository

import com.example.domain.result.OutCome
import com.example.movie_feature.domain.models.CategoriesResponse
import com.example.movie_feature.domain.models.TrendingTvResponse

interface TvRepository {
    suspend fun getTvCategoriesList(): OutCome<CategoriesResponse>
    suspend fun getTvCategoryList(page:Int,categoryId: Int): OutCome<TrendingTvResponse>
}