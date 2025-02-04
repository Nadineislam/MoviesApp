package com.example.movie_feature.domain.models

import com.google.gson.annotations.SerializedName

data class TrendingPeopleResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val trendingPeople: List<People>,
)
