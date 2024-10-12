package com.example.domain.models

import com.google.gson.annotations.SerializedName

data class TrendingTvResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val trendingTv: List<Tv>
)
