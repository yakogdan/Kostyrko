package com.yakogdan.kinopoiskkostyrko.data.network.dto

import com.google.gson.annotations.SerializedName

data class TopFilms(
    @SerializedName("items")
    val items: List<Item>,
    @SerializedName("total")
    val total: Int,
    @SerializedName("totalPages")
    val totalPages: Int
)