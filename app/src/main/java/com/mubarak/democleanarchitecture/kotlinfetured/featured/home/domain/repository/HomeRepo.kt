package com.mubarak.democleanarchitecture.kotlinfetured.featured.home.domain.repository

import com.mubarak.democleanarchitecture.kotlinfetured.featured.home.data.model.HomeDataClass

interface HomeRepo {
    suspend fun getHomePageData(): List<HomeDataClass>
}