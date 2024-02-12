package com.yakogdan.kinopoiskkostyrko.presentation.films

import com.yakogdan.kinopoiskkostyrko.domain.entity.Film
import kotlinx.coroutines.flow.StateFlow

interface FilmsComponent {

    val model: StateFlow<PopularStore.State>

    fun onFilmItemClick(film: Film)

    fun addToFavourite(film: Film)
}