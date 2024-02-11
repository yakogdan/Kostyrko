package com.yakogdan.kinopoiskkostyrko.presentation.favourite

import com.yakogdan.kinopoiskkostyrko.domain.entity.Film
import kotlinx.coroutines.flow.StateFlow

interface FavouriteComponent {

    val model: StateFlow<FavouriteStore.State>

    fun onFilmItemClick(film: Film)
}