package com.example.movie_feature.domain.models

data class TrendingPeopleResponse(
    val page: Int,
    val trendingPeople: List<People>,
)
