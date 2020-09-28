package com.dicoding.core.data.source.remote

import android.util.Log
import com.dicoding.core.data.source.remote.network.ApiResponse
import com.dicoding.core.data.source.remote.network.ApiService
import com.dicoding.core.data.source.remote.response.TourismResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

// Mengubah constructor yang sebelumnya JSONHelper menjadi ApiService
class RemoteDataSource(private val apiService: ApiService) {
    companion object {
        const val TAG = "RemoteDataSource"

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(apiService: ApiService): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(apiService)
            }
    }

    // Menggunakan Flow interface seperti Flow pada RXJava2
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