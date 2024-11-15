package com.mubarak.democleanarchitecture.kotlinfetured.featured.home.data.repository

import com.mubarak.democleanarchitecture.kotlinfetured.di.network.ApiService
import com.mubarak.democleanarchitecture.kotlinfetured.featured.home.data.model.HomeDataClass
import com.mubarak.democleanarchitecture.kotlinfetured.featured.home.domain.repository.HomeRepo
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(private val apiService: ApiService) : HomeRepo {
    override suspend fun getHomePageData(): List<HomeDataClass> {
        return apiService.getPostData()
    }
}