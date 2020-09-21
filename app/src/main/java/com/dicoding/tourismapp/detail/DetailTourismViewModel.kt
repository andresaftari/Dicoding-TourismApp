package com.dicoding.tourismapp.detail

import androidx.lifecycle.ViewModel
import com.dicoding.tourismapp.core.domain.model.Tourism
import com.dicoding.tourismapp.core.domain.usecase.ITourismUseCase

// Mengubah constructor DetailTourismViewModel dari TourismRepository menjadi ITourismUseCase
class DetailTourismViewModel(private val iTourismUseCase: ITourismUseCase) : ViewModel() {
    fun setFavoriteTourism(tourism: Tourism, newStatus: Boolean) =
        iTourismUseCase.setFavoriteTourism(tourism, newStatus)
}