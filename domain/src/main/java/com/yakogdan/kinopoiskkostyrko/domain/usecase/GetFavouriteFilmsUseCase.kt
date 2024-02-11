package com.yakogdan.kinopoiskkostyrko.domain.usecase

import com.yakogdan.kinopoiskkostyrko.domain.repository.FavouriteRepository
import javax.inject.Inject

class GetFavouriteFilmsUseCase @Inject constructor(
    private val repository: FavouriteRepository
) {
    operator fun invoke() = repository.favouriteFilms
}