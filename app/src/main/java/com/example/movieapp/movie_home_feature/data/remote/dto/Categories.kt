package com.example.movieapp.movie_home_feature.data.remote.dto

import com.google.gson.annotations.SerializedName

data class Categories(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)