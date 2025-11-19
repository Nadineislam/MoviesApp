package com.example.movie_feature.data.remote.models

import com.google.gson.annotations.SerializedName

data class TrendingTvDtoResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val trendingTv: List<TvDto>
)
