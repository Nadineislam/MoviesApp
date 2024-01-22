package com.example.movieapp.movie_home_feature.data.repository

import com.example.movieapp.movie_home_feature.data.remote.MoviesApi
import com.example.movieapp.movie_home_feature.data.remote.dto.CategoriesResponse
import com.example.movieapp.movie_home_feature.data.remote.dto.TrendingMoviesResponse
import com.example.movieapp.movie_home_feature.domain.repository.MoviesRepository
import retrofit2.Response
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(private val moviesApi: MoviesApi) : MoviesRepository {
    override suspend fun getMoviesCategoriesList(): Response<CategoriesResponse> =
        moviesApi.getMoviesCategoriesList()

    override suspend fun getMovieCategoryList(page:Int,categoryId: Int): Response<TrendingMoviesResponse> =
        moviesApi.getMovieCategoryList(page= page,categoryId = categoryId)

}