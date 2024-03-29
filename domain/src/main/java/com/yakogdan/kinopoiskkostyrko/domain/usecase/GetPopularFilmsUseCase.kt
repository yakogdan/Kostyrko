package com.yakogdan.kinopoiskkostyrko.domain.usecase

import com.yakogdan.kinopoiskkostyrko.domain.repository.FilmsRepository
import javax.inject.Inject

class GetPopularFilmsUseCase @Inject constructor(
    private val repository: FilmsRepository
) {
    suspend operator fun invoke() = repository.getPopularFilms()
}