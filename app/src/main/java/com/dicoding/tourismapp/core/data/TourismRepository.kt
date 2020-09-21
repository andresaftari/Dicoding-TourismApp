package com.dicoding.tourismapp.core.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.dicoding.tourismapp.core.data.source.local.LocalDataSource
import com.dicoding.tourismapp.core.data.source.remote.RemoteDataSource
import com.dicoding.tourismapp.core.data.source.remote.network.ApiResponse
import com.dicoding.tourismapp.core.data.source.remote.response.TourismResponse
import com.dicoding.tourismapp.core.domain.model.Tourism
import com.dicoding.tourismapp.core.utils.AppExecutors
import com.dicoding.tourismapp.core.utils.DataMapper

class TourismRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) {
    companion object {
        @Volatile
        private var instance: TourismRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource,
            appExecutors: AppExecutors
        ): TourismRepository =
            instance ?: synchronized(this) {
                instance ?: TourismRepository(remoteData, localData, appExecutors)
            }
    }

    // Mengubah tipe data TourismRepository dari TourismEntity menjadi Tourism
    fun getAllTourism(): LiveData<Resource<List<Tourism>>> =
        object : NetworkBoundResource<List<Tourism>, List<TourismResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<Tourism>> =
                Transformations.map(localDataSource.getAllTourism()) {
                    DataMapper.mapEntitiesToDomain(it)
                }

            override fun shouldFetch(data: List<Tourism>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<TourismResponse>>> =
                remoteDataSource.getAllTourism()

            override fun saveCallResult(data: List<TourismResponse>) {
                val tourismList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertTourism(tourismList)
            }
        }.asLiveData()

    // Mengubah tipe data TourismRepository dari TourismEntity menjadi Tourism
    fun getFavoriteTourism(): LiveData<List<Tourism>> =
        Transformations.map(localDataSource.getFavoriteTourism()) {
            DataMapper.mapEntitiesToDomain(it)
        }

    // Mengubah tipe data TourismRepository dari TourismEntity menjadi Tourism
    fun setFavoriteTourism(tourism: Tourism, state: Boolean) {
        val tourismEntity = DataMapper.mapDomainToEntities(tourism)
        appExecutors.diskIO().execute { localDataSource.setFavoriteTourism(tourismEntity, state) }
    }
}

