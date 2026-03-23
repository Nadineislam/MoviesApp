package com.example.movie_feature.domain.models

data class TrendingMoviesResponse(
    val page: Int,
    val trendingMovies: List<Movies>
)

