package com.dicoding.tourismapp.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.tourismapp.core.domain.usecase.ITourismUseCase

// Mengubah constructor HomeViewModel dari TourismRepository menjadi ITourismUseCase
class HomeViewModel(iTourismUseCase: ITourismUseCase) : ViewModel() {
    val tourism = iTourismUseCase.getAllTourism().asLiveData()
}