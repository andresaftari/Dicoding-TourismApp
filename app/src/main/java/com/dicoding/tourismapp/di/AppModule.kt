package com.dicoding.tourismapp.di

import com.dicoding.tourismapp.core.domain.usecase.ITourismUseCase
import com.dicoding.tourismapp.core.domain.usecase.TourismInteractor
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {
    @Binds
    abstract fun provideTourismUseCase(tourismInteractor: TourismInteractor): ITourismUseCase
}