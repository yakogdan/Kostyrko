package com.yakogdan.kinopoiskkostyrko.domain.usecase

import com.yakogdan.kinopoiskkostyrko.domain.repository.FilmsRepository
import javax.inject.Inject

class ChangeFilmsStateUseCase @Inject constructor(
    private val repository: FilmsRepository
) {

    suspend fun changeToFavourite() = repository.changeToFavourite()

    suspend fun changeToPopular() = repository.changeToPopular()
}