package com.dicoding.tourismapp.core.data.source.remote.network

import com.dicoding.tourismapp.core.data.source.remote.response.ListTourismResponse
import io.reactivex.Flowable
import retrofit2.Call
import retrofit2.http.GET

// Deklarasi API endpoint dan data yang akan diambil menggunakan GET
interface ApiService {
    @GET("list")
    // Flowable untuk mengambil data dan Completable untuk memasukkan data
    fun getList(): Flowable<ListTourismResponse>
}