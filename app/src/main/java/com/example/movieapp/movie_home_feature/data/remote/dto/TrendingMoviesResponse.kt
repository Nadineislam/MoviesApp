package com.example.movieapp.movie_home_feature.data.remote.dto

import com.google.gson.annotations.SerializedName

data class TrendingMoviesResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val trendingMovies: List<Movies>
)
