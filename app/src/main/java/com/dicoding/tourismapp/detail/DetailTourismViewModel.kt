package com.dicoding.tourismapp.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.dicoding.tourismapp.core.domain.model.Tourism
import com.dicoding.tourismapp.core.domain.usecase.ITourismUseCase

// Ubah @Inject menjadi @ViewModelInject
class DetailTourismViewModel @ViewModelInject constructor(private val iTourismUseCase: ITourismUseCase) : ViewModel() {
    fun setFavoriteTourism(tourism: Tourism, newStatus: Boolean) =
        iTourismUseCase.setFavoriteTourism(tourism, newStatus)
}