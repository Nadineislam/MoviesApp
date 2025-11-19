package com.example.movie_feature.data.remote.models

import com.example.movie_feature.domain.models.People
import com.google.gson.annotations.SerializedName

data class TrendingPeopleDtoResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val trendingPeople: List<PeopleDto>,
)
