package com.example.movieapp.movie_home_feature.data.remote

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
    suspend fun getMoviesCategories(@Query("api_key") apiKey: String): Response<CategoriesResponse>

    @GET("genre/tv/list")
    suspend fun getTvCategories(@Query("api_key") apiKey: String): Response<CategoriesResponse>

    @GET("discover/movie")
    suspend fun getMovieCategories(
        @Query("api_key") apiKey: String, @Query("with_genres") categoryNumber: Int
    ): Response<TrendingMoviesResponse>

    @GET("discover/tv")
    suspend fun getTvCategories(
        @Query("api_key") apiKey: String, @Query("with_genres") categoryNumber: Int
    ): Response<TrendingTvResponse>

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val API_KEY = "15bfb3d770d73513c71ab9f787cbe27b"
        const val IMAGE_BASE_URL = "https://image.tmdb.org/"
        const val PIC_POSTER_PATH = "t/p/original"

    }
}