package com.dicoding.tourismapp.detail

import androidx.lifecycle.ViewModel
import com.dicoding.tourismapp.core.data.TourismRepository
import com.dicoding.tourismapp.core.data.source.local.entity.TourismEntity
import com.dicoding.tourismapp.core.domain.model.Tourism

// Mengubah tipe data DetailTourismViewModel dari TourismEntity menjadi Tourism
class DetailTourismViewModel(private val tourismRepository: TourismRepository) : ViewModel() {
    fun setFavoriteTourism(tourism: Tourism, newStatus:Boolean) = tourismRepository.setFavoriteTourism(tourism, newStatus)
}

