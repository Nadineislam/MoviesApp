package com.example.movie_feature.domain.models

import com.google.gson.annotations.SerializedName

data class Movies(
    @SerializedName("title")
    val name: String,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("overview")
    val overView: String,
    @SerializedName("backdrop_path")
    val logo: String
)
