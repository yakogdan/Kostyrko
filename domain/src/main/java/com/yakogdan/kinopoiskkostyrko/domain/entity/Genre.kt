package com.yakogdan.kinopoiskkostyrko.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Genre(
    val genre: String
) : Parcelable