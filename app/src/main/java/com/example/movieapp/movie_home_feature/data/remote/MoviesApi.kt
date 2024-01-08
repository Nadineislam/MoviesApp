package com.example.movieapp.movie_home_feature.data.remote

import com.example.movieapp.core.utils.Constants.Companion.API_KEY
import com.example.movieapp.movie_home_feature.data.remote.dto.CategoriesResponse
import com.example.movieapp.movie_home_feature.data.remote.dto.TrendingMoviesResponse
import com.example.movieapp.movie_home_feature.data.remote.dto.TrendingPeopleResponse
import com.example.movieapp.movie_home_feature.data.remote.dto.TrendingTvResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {
    @GET("trending/movie/day")
    suspend fun getTrendingMovies(
        @Query("api_key") apiKey: String = API_KEY
    ): Response<TrendingMoviesResponse>

    @GET("trending/tv/week")
    suspend fun getTrendingTv(@Query("api_key") apiKey: String = API_KEY): Response<TrendingTvResponse>

    @GET("trending/person/week")
    suspend fun getTrendingPeople(@Query("api_key") apiKey: String = API_KEY): Response<TrendingPeopleResponse>

    @GET("search/collection")
    suspend fun getSearchedMovie(
        @Query("query") searchedItem: String, @Query("api_key") apiKey: String = API_KEY
    ): Response<TrendingTvResponse>

    @GET("genre/movie/list")
    suspend fun getMoviesCategoriesList(@Query("api_key") apiKey: String = API_KEY): Response<CategoriesResponse>

    @GET("genre/tv/list")
    suspend fun getTvCategoriesList(@Query("api_key") apiKey: String = API_KEY): Response<CategoriesResponse>

    @GET("discover/movie")
    suspend fun getMovieCategoryList(
        @Query("api_key") apiKey: String = API_KEY, @Query("with_genres") categoryId: Int
    ): Response<TrendingMoviesResponse>

    @GET("discover/tv")
    suspend fun getTvCategoryList(
        @Query("api_key") apiKey: String = API_KEY, @Query("with_genres") categoryId: Int
    ): Response<TrendingTvResponse>

}