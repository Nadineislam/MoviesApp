package com.example.movie_feature.domain.models

data class Movies(
    val name: String,
    val posterPath: String?,
    val voteAverage: Double,
    val overView: String,
    val logo: String?
)
