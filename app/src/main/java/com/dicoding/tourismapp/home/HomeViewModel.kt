package com.dicoding.tourismapp.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.tourismapp.core.domain.usecase.ITourismUseCase

// Ubah @Inject menjadi @ViewModelInject
class HomeViewModel @ViewModelInject constructor(iTourismUseCase: ITourismUseCase) : ViewModel() {
    val tourism = iTourismUseCase.getAllTourism().asLiveData()
}