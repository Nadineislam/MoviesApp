package com.example.data.remote

import com.example.domain.models.CategoriesResponse
import com.example.domain.models.TrendingMoviesResponse
import com.example.domain.models.TrendingPeopleResponse
import com.example.domain.models.TrendingTvResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {
    @GET("trending/movie/day")
    suspend fun getTrendingMovies(): Response<TrendingMoviesResponse>

    @GET("trending/tv/week")
    suspend fun getTrendingTv(): Response<TrendingTvResponse>

    @GET("trending/person/week")
    suspend fun getTrendingPeople(): Response<TrendingPeopleResponse>

    @GET("search/collection")
    suspend fun getSearchedMovie(
        @Query("query") searchedItem: String): Response<TrendingTvResponse>

    @GET("genre/movie/list")
    suspend fun getMoviesCategoriesList(): Response<CategoriesResponse>

    @GET("genre/tv/list")
    suspend fun getTvCategoriesList(): Response<CategoriesResponse>

    @GET("discover/movie")
    suspend fun getMovieCategoryList(
        @Query("page") page: Int,
        @Query("with_genres") categoryId: Int
    ): Response<TrendingMoviesResponse>

    @GET("discover/tv")
    suspend fun getTvCategoryList(
        @Query("page") page: Int,
        @Query("with_genres") categoryId: Int
    ): Response<TrendingTvResponse>

}