package com.example.movie_feature.data.mappers

import com.example.movie_feature.data.remote.models.TrendingTvDtoResponse
import com.example.movie_feature.data.remote.models.TvDto
import com.example.movie_feature.domain.models.TrendingTvResponse
import com.example.movie_feature.domain.models.Tv

fun TrendingTvDtoResponse.toTvModel(): TrendingTvResponse {
    return TrendingTvResponse(
        page = this.page,
        trendingTv = this.trendingTv.map { it.toTv() }
    )
}

fun TvDto.toTv(): Tv {
    return Tv(
        poster = this.poster,
        name = this.name,
        overView = this.overView,
        voteAverage = this.voteAverage
    )
}