package com.mubarak.democleanarchitecture.kotlinfetured.di.module

import com.mubarak.democleanarchitecture.kotlinfetured.di.network.ApiService
import com.mubarak.democleanarchitecture.kotlinfetured.featured.home.domain.usecase.HomePageUseCase
import com.mubarak.democleanarchitecture.kotlinfetured.featured.home.domain.repository.HomeRepo
import com.mubarak.democleanarchitecture.kotlinfetured.featured.home.data.repository.HomeRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomePageModule {

    @Provides
    @Singleton
    fun provideHomeRepository(apiService: ApiService): HomeRepo = HomeRepositoryImpl(apiService)

    @Provides
    @Singleton
    fun provideHomeUserCase(homeRepo: HomeRepo): HomePageUseCase =
        HomePageUseCase(homeRepo = homeRepo)
}