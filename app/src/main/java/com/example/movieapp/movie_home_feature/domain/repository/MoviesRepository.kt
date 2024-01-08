package com.example.movieapp.movie_home_feature.domain.repository

import com.example.movieapp.movie_home_feature.data.remote.dto.CategoriesResponse
import com.example.movieapp.movie_home_feature.data.remote.dto.TrendingMoviesResponse
import retrofit2.Response

interface MoviesRepository {
    suspend fun getMoviesCategoriesList(): Response<CategoriesResponse>
    suspend fun getMovieCategoryList(categoryId: Int): Response<TrendingMoviesResponse>

}