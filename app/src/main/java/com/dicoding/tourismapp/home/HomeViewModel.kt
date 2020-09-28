package com.dicoding.tourismapp.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.tourismapp.core.domain.usecase.ITourismUseCase
import javax.inject.Inject

// Tambahkan injection
class HomeViewModel @Inject constructor(iTourismUseCase: ITourismUseCase) : ViewModel() {
    val tourism = iTourismUseCase.getAllTourism().asLiveData()
}