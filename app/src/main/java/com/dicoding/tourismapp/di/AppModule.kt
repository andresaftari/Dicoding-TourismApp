package com.dicoding.tourismapp.di

import com.dicoding.tourismapp.core.domain.usecase.ITourismUseCase
import com.dicoding.tourismapp.core.domain.usecase.TourismInteractor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

// Ubah anotasi @Module menjadi seperti berikut
@Module
@InstallIn(ActivityComponent::class)
abstract class AppModule {
    @Binds
    abstract fun provideTourismUseCase(tourismInteractor: TourismInteractor): ITourismUseCase
}