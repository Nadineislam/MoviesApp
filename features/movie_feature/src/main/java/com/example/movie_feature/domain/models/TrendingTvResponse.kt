package com.example.movie_feature.domain.models

data class TrendingTvResponse(
    val page: Int,
    val trendingTv: List<Tv>
)
