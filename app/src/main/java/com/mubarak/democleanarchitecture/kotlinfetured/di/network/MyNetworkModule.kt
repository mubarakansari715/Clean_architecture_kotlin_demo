package com.mubarak.democleanarchitecture.kotlinfetured.di.network

import com.mubarak.democleanarchitecture.kotlinfetured.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MyNetworkModule {

    @Singleton
    @Provides
    fun getRetrofitClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofitClient: Retrofit): ApiService {
        return retrofitClient.create(ApiService::class.java)
    }
}