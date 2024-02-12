package com.yakogdan.kinopoiskkostyrko.domain.usecase

import com.yakogdan.kinopoiskkostyrko.domain.repository.FilmsRepository
import javax.inject.Inject

class ObserveFavouriteStateUseCase @Inject constructor(
    private val repository: FilmsRepository
) {
    operator fun invoke(filmId: Int) = repository.observeIsFavourite(filmId)
}