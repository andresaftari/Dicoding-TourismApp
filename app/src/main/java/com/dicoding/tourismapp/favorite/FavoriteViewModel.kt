package com.dicoding.tourismapp.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.tourismapp.core.domain.usecase.ITourismUseCase
import javax.inject.Inject

// Tambahkan injection
class FavoriteViewModel @Inject constructor(iTourismUseCase: ITourismUseCase) : ViewModel() {
    val favoriteTourism = iTourismUseCase.getFavoriteTourism().asLiveData()
}