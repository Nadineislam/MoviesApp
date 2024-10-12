package com.example.domain.models

import com.google.gson.annotations.SerializedName

data class TrendingMoviesResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val trendingMovies: List<Movies>
)
