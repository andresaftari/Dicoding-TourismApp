package com.dicoding.core.domain.usecase

import com.dicoding.core.domain.model.Tourism
import com.dicoding.core.domain.repository.ITourismRepository

class TourismInteractor(private val tourismRepository: ITourismRepository) : ITourismUseCase {
    override fun getAllTourism() = tourismRepository.getAllTourism()
    override fun getFavoriteTourism() = tourismRepository.getFavoriteTourism()
    override fun setFavoriteTourism(tourism: Tourism, state: Boolean) =
        tourismRepository.setFavoriteTourism(tourism, state)
}