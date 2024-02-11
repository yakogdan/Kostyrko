package com.yakogdan.kinopoiskkostyrko.domain.usecase

import com.yakogdan.kinopoiskkostyrko.domain.repository.PopularRepository
import javax.inject.Inject

class GetFilmDetailUseCase @Inject constructor(
    private val repository: PopularRepository
) {
    suspend operator fun invoke(filmId: Int) = repository.getFilmDetail(filmId)
}