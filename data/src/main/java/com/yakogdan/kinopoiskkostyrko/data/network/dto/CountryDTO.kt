package com.yakogdan.kinopoiskkostyrko.data.network.dto

import com.google.gson.annotations.SerializedName

data class CountryDTO(
    @SerializedName("country")
    val country: String
)