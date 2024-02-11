package com.yakogdan.kinopoiskkostyrko.domain.usecase

import com.yakogdan.kinopoiskkostyrko.domain.repository.PopularRepository
import javax.inject.Inject

class GetPopularFilmsUseCase @Inject constructor(
    private val repository: PopularRepository
) {
    suspend operator fun invoke() = repository.getPopularFilms()
}