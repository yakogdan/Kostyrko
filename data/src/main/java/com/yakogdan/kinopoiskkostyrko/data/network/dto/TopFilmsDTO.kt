package com.yakogdan.kinopoiskkostyrko.data.network.dto

import com.google.gson.annotations.SerializedName

data class TopFilmsDTO(
    @SerializedName("items")
    val films: List<FilmDTO>,
    @SerializedName("total")
    val total: Int,
    @SerializedName("totalPages")
    val totalPages: Int
)