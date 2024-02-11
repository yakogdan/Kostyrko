package com.yakogdan.kinopoiskkostyrko.data.network.dto

import com.google.gson.annotations.SerializedName

data class GenreDTO(
    @SerializedName("genre")
    val genre: String
)