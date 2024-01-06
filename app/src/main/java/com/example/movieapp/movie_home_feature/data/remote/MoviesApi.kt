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
    suspend fun getCategoriesList(@Query("api_key") apiKey: String= API_KEY): Response<CategoriesResponse>

    @GET("genre/tv/list")
    suspend fun getTvCategories(@Query("api_key") apiKey: String): Response<CategoriesResponse>

    @GET("discover/movie")
    suspend fun getMovieCategoriesList(
        @Query("api_key") apiKey: String= API_KEY, @Query("with_genres") categoryId: Int
    ): Response<TrendingMoviesResponse>

    @GET("discover/tv")
    suspend fun getTvCategories(
        @Query("api_key") apiKey: String, @Query("with_genres") categoryNumber: Int
    ): Response<TrendingTvResponse>

}