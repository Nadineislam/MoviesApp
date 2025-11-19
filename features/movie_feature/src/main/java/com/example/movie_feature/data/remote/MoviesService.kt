package com.example.movie_feature.data.remote

import com.example.movie_feature.data.remote.models.CategoriesDtoResponse
import com.example.movie_feature.data.remote.models.TrendingMoviesDtoResponse
import com.example.movie_feature.data.remote.models.TrendingPeopleDtoResponse
import com.example.movie_feature.data.remote.models.TrendingTvDtoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesService {
    @GET("trending/movie/day")
    suspend fun getTrendingMovies(): Response<TrendingMoviesDtoResponse>

    @GET("trending/tv/week")
    suspend fun getTrendingTv(): Response<TrendingTvDtoResponse>

    @GET("trending/person/week")
    suspend fun getTrendingPeople(): Response<TrendingPeopleDtoResponse>

    @GET("search/collection")
    suspend fun getSearchedMovie(
        @Query("query") searchedItem: String): Response<TrendingTvDtoResponse>

    @GET("genre/movie/list")
    suspend fun getMoviesCategoriesList(): Response<CategoriesDtoResponse>

    @GET("genre/tv/list")
    suspend fun getTvCategoriesList(): Response<CategoriesDtoResponse>

    @GET("discover/movie")
    suspend fun getMovieCategoryList(
        @Query("page") page: Int,
        @Query("with_genres") categoryId: Int
    ): Response<TrendingMoviesDtoResponse>

    @GET("discover/tv")
    suspend fun getTvCategoryList(
        @Query("page") page: Int,
        @Query("with_genres") categoryId: Int
    ): Response<TrendingTvDtoResponse>

}