package com.yakogdan.kinopoiskkostyrko.domain.repository

import com.yakogdan.kinopoiskkostyrko.domain.entity.Film
import kotlinx.coroutines.flow.Flow

interface FavouriteRepository {

    val favouriteFilms: Flow<List<Film>>

    fun observeIsFavourite(filmId: Int): Flow<Boolean>

    suspend fun addToFavourite(film: Film)

    suspend fun removeFromFavourite(filmId: Int)
}