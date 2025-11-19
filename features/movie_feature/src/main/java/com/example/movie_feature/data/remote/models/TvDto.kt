package com.example.movie_feature.data.remote.models

import com.google.gson.annotations.SerializedName

data class TvDto(
    @SerializedName("name")
    val name: String,
    @SerializedName("poster_path")
    val poster: String?,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("overview")
    val overView: String
)
