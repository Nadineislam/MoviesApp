package com.example.movie_feature.data.mappers

import com.example.movie_feature.data.remote.models.PeopleDto
import com.example.movie_feature.data.remote.models.TrendingPeopleDtoResponse
import com.example.movie_feature.domain.models.People
import com.example.movie_feature.domain.models.TrendingPeopleResponse

fun TrendingPeopleDtoResponse.toPeopleModel(): TrendingPeopleResponse {
    return TrendingPeopleResponse(
        page = this.page,
        trendingPeople = this.trendingPeople.map { it.toPeople() }
    )
}

fun PeopleDto.toPeople(): People {
    return People(
        name = this.name,
        poster = this.poster ?: ""
    )
}