package com.yakogdan.kinopoiskkostyrko.domain.usecase

import com.yakogdan.kinopoiskkostyrko.domain.entity.Film
import com.yakogdan.kinopoiskkostyrko.domain.repository.FilmsRepository
import javax.inject.Inject

class ChangeFavouriteStateUseCase @Inject constructor(
    private val repository: FilmsRepository
) {

    suspend fun addToFavourite(film: Film) = repository.addToFavourite(film)

    suspend fun removeFromFavourite(filmId: Int) = repository.removeFromFavourite(filmId)
}