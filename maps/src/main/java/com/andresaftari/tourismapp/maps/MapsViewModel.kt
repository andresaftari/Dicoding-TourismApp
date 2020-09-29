package com.andresaftari.tourismapp.maps

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.core.domain.usecase.ITourismUseCase

class MapsViewModel(iTourismUseCase: ITourismUseCase) : ViewModel() {
    val tourism = iTourismUseCase.getAllTourism().asLiveData()
}