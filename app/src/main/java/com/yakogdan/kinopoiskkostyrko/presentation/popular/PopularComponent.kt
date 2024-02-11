package com.yakogdan.kinopoiskkostyrko.presentation.popular

import com.yakogdan.kinopoiskkostyrko.domain.entity.Film
import kotlinx.coroutines.flow.StateFlow

interface PopularComponent {

    val model: StateFlow<PopularStore.State>

    fun onFilmItemClick(film: Film)

    fun addToFavourite(film: Film)
}