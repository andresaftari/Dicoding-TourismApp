package com.dicoding.tourismapp.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.tourismapp.core.domain.usecase.ITourismUseCase

// Mengubah constructor FavoriteViewModel dari TourismRepository menjadi ITourismUseCase
class FavoriteViewModel(iTourismUseCase: ITourismUseCase) : ViewModel() {
    val favoriteTourism = iTourismUseCase.getFavoriteTourism().asLiveData()
}