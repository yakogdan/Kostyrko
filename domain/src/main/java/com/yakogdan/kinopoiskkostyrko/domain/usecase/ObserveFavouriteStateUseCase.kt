package com.yakogdan.kinopoiskkostyrko.domain.usecase

import com.yakogdan.kinopoiskkostyrko.domain.repository.FavouriteRepository
import javax.inject.Inject

class ObserveFavouriteStateUseCase @Inject constructor(
    private val repository: FavouriteRepository
) {
    operator fun invoke(filmId: Int) = repository.observeIsFavourite(filmId)
}