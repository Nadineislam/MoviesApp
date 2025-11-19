package com.example.movie_feature.data.remote.models

import com.google.gson.annotations.SerializedName

data class TrendingMoviesDtoResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val trendingMovies: List<MoviesDto>
)

