package com.example.movie_feature.domain.repository

import com.example.domain.result.OutCome
import com.example.movie_feature.domain.models.CategoriesResponse
import com.example.movie_feature.domain.models.TrendingMoviesResponse

interface MoviesRepository {
    suspend fun getMoviesCategoriesList(): OutCome<CategoriesResponse>
    suspend fun getMovieCategoryList(page:Int,categoryId: Int): OutCome<TrendingMoviesResponse>

}