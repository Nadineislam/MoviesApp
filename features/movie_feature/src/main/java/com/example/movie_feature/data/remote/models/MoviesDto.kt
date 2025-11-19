package com.example.movie_feature.data.remote.models

import com.google.gson.annotations.SerializedName

data class MoviesDto(
    @SerializedName("title")
    val name: String,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("overview")
    val overView: String,
    @SerializedName("backdrop_path")
    val logo: String?
)
