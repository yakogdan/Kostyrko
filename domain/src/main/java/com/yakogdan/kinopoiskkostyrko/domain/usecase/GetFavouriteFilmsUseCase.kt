package com.yakogdan.kinopoiskkostyrko.domain.usecase

import com.yakogdan.kinopoiskkostyrko.domain.repository.FilmsRepository
import javax.inject.Inject

class GetFavouriteFilmsUseCase @Inject constructor(
    private val repository: FilmsRepository
) {
    operator fun invoke() = repository.favouriteFilms
}