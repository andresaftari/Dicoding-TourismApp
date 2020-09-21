package com.dicoding.tourismapp.favorite

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.dicoding.tourismapp.core.domain.usecase.ITourismUseCase

// Mengubah constructor FavoriteViewModel dari TourismRepository menjadi ITourismUseCase
class FavoriteViewModel(iTourismUseCase: ITourismUseCase) : ViewModel() {
    // Ambil data menggunakan LiveDataReactiveStreams dan fromPublisher()
    val favoriteTourism =
        LiveDataReactiveStreams.fromPublisher(iTourismUseCase.getFavoriteTourism())
}