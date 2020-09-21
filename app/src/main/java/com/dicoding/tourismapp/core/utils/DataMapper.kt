package com.dicoding.tourismapp.core.utils

import com.dicoding.tourismapp.core.data.source.local.entity.TourismEntity
import com.dicoding.tourismapp.core.data.source.remote.response.TourismResponse
import com.dicoding.tourismapp.core.domain.model.Tourism

object DataMapper {
    fun mapResponsesToEntities(input: List<TourismResponse>): List<TourismEntity> {
        val tourismList = ArrayList<TourismEntity>()
        input.map {
            val tourism = TourismEntity(
                tourismId = it.id,
                description = it.description,
                name = it.name,
                address = it.address,
                latitude = it.latitude,
                longitude = it.longitude,
                like = it.like,
                image = it.image,
                isFavorite = false
            )
            tourismList.add(tourism)
        }
        return tourismList
    }

    // Function untuk mengubah TourismEntity (Room model) ke Tourism (domain model)
    fun mapEntitiesToDomain(entities: List<TourismEntity>): List<Tourism> =
        entities.map {
            Tourism(
                it.tourismId,
                it.description,
                it.name,
                it.address,
                it.latitude,
                it.longitude,
                it.like,
                it.image,
                it.isFavorite
            )
        }

    // Function untuk mengubah domain model ke Room model
    fun mapDomainToEntities(entity: Tourism) = TourismEntity(
        entity.tourismId,
        entity.description,
        entity.name,
        entity.address,
        entity.latitude,
        entity.longitude,
        entity.like,
        entity.image,
        entity.isFavorite
    )
}