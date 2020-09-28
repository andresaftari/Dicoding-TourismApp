package com.dicoding.tourismapp.favorite

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.tourismapp.core.domain.usecase.ITourismUseCase

// Ubah @Inject menjadi @ViewModelInject
class FavoriteViewModel @ViewModelInject constructor(iTourismUseCase: ITourismUseCase) : ViewModel() {
    val favoriteTourism = iTourismUseCase.getFavoriteTourism().asLiveData()
}