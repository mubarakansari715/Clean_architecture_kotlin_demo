package com.mubarak.democleanarchitecture.kotlinfetured.di.network

import com.mubarak.democleanarchitecture.kotlinfetured.featured.home.data.model.HomeDataClass
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface ApiService {

    @GET("photos")
    suspend fun getPostData(): List<HomeDataClass>
}