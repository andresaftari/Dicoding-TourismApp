package com.dicoding.tourismapp.core.data

import com.dicoding.tourismapp.core.data.source.remote.network.ApiResponse
import com.dicoding.tourismapp.core.utils.AppExecutors
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

abstract class NetworkBoundResource<ResultType, RequestType>(private val mExecutors: AppExecutors) {
    private val result = PublishSubject.create<Resource<ResultType>>()
    private val mCompositeDisposable = CompositeDisposable()

    init {
        @Suppress("LeakingThis")
        val dbSource = loadFromDB()

        val db = dbSource
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1) // Menggunakan operator take(1) untuk mengambil data dari API sekali saja
            .subscribe { value ->
                dbSource.unsubscribeOn(Schedulers.io()) // UnsubscribeOn() untuk keluar dari thread Schedulers

                if (shouldFetch(value)) fetchFromNetwork()
                else result.onNext(Resource.Success(value)) // Meng-emit setiap response menggunakan fungsi onNext
            }
        mCompositeDisposable.add(db)
    }

    protected open fun onFetchFailed() {}

    protected abstract fun loadFromDB(): Flowable<ResultType>

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract fun createCall(): Flowable<ApiResponse<RequestType>>

    protected abstract fun saveCallResult(data: RequestType)

    private fun fetchFromNetwork() {
        val apiResponse = createCall()

        result.onNext(Resource.Loading(null))
        val response = apiResponse
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)  // Menggunakan operator take(1) untuk mengambil data dari API sekali saja
            .doOnComplete { mCompositeDisposable.dispose() } // Setelah complete hapus (dispose) prosesnya
            .subscribe { response -> // Proses ApiResponse dijalankan disini
                when (response) {
                    is ApiResponse.Success -> {
                        saveCallResult(response.data)
                        val dbSources = loadFromDB()

                        dbSources.apply {
                            subscribeOn(Schedulers.computation())
                            observeOn(AndroidSchedulers.mainThread())
                            take(1)
                            subscribe {
                                dbSources.unsubscribeOn(Schedulers.io())
                                result.onNext(Resource.Success(it))
                            }
                        }
                    }
                    is ApiResponse.Empty -> {
                        val dbSources = loadFromDB()

                        dbSources.apply {
                            subscribeOn(Schedulers.computation())
                            observeOn(AndroidSchedulers.mainThread())
                            take(1)
                            subscribe {
                                dbSources.unsubscribeOn(Schedulers.io())
                                result.onNext(Resource.Success(it))
                            }
                        }
                    }
                    is ApiResponse.Error -> {
                        onFetchFailed()
                        result.onNext(Resource.Error(response.errorMessage, null))
                    }
                }
            }
        mCompositeDisposable.add(response)
    }

    // Mengubah asLiveData ke asFlowable dengan Backpressure strategy Buffer
    fun asFlowable(): Flowable<Resource<ResultType>> =
        result.toFlowable(BackpressureStrategy.BUFFER)
}