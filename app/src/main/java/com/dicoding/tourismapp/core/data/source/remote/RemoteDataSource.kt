package com.dicoding.tourismapp.core.data.source.remote

import android.util.Log
import com.dicoding.tourismapp.core.data.source.remote.network.ApiResponse
import com.dicoding.tourismapp.core.data.source.remote.network.ApiService
import com.dicoding.tourismapp.core.data.source.remote.response.TourismResponse
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

// Mengubah constructor yang sebelumnya JSONHelper menjadi ApiService
class RemoteDataSource private constructor(private val apiService: ApiService) {
    companion object {
        const val TAG = "RemoteDataSource"

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(apiService: ApiService): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(apiService)
            }
    }

    // Menggunakan fungsi toFlowable
    fun getAllTourism(): Flowable<ApiResponse<List<TourismResponse>>> {
        // Menggunakan PublishSubject untuk menampung data di dalam ApiResponse
        val resultData = PublishSubject.create<ApiResponse<List<TourismResponse>>>()

        // Get data from remote API
        val client = apiService.getList()

        client.apply {
            subscribeOn(Schedulers.computation())
            observeOn(AndroidSchedulers.mainThread())
            // Menggunakan operator take(1) untuk mengambil data dari API sekali saja
            take(1)
            subscribe({ response ->
                val dataArray = response.places
                // Meng-emit setiap response menggunakan fungsi onNext
                resultData.onNext(if (dataArray.isNotEmpty()) ApiResponse.Success(dataArray) else ApiResponse.Empty)
            }, { error ->
                // Meng-emit setiap response menggunakan fungsi onNext
                resultData.onNext(ApiResponse.Error(error.message.toString()))
                Log.i(TAG, error.message.toString())
            })
        }
        // Menggunakan fungsi toFlowable untuk mengubah Subject menjadi Flowable
        // Menggunakan Backpressure strategy Buffer untuk mengambil tiap data walaupun ter-delay
        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }
}