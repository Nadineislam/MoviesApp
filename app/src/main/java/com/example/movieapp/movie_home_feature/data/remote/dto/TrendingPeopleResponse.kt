package com.example.movieapp.movie_home_feature.data.remote.dto

import com.google.gson.annotations.SerializedName

data class TrendingPeopleResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val trendingPeople: List<People>,
)
