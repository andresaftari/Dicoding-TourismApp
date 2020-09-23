package com.dicoding.tourismapp.core.data.source.remote

import android.util.Log
import com.dicoding.tourismapp.core.data.source.remote.network.ApiResponse
import com.dicoding.tourismapp.core.data.source.remote.network.ApiService
import com.dicoding.tourismapp.core.data.source.remote.response.TourismResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

// Mengubah constructor yang sebelumnya JSONHelper menjadi ApiService
@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {
    companion object {
        const val TAG = "RemoteDataSource"
    }

    // Menggunakan Flow interface seperti Flowable pada RXJava2
    suspend fun getAllTourism(): Flow<ApiResponse<List<TourismResponse>>> {

        // Get data from remote API
        return flow {
            try {
                val response = apiService.getList()
                val dataArray = response.places

                if (dataArray.isNotEmpty()) emit(ApiResponse.Success(response.places))
                else emit(ApiResponse.Empty)
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.i(TAG, "${e.message} --- ${e.printStackTrace()}")
            }
        }.flowOn(Dispatchers.IO)
    }
}