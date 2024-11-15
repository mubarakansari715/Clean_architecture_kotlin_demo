package com.mubarak.democleanarchitecture.kotlinfetured.featured.home.domain.usecase

import android.util.Log
import com.mubarak.democleanarchitecture.kotlinfetured.featured.home.domain.repository.HomeRepo
import com.mubarak.democleanarchitecture.kotlinfetured.utils.NetworkResponse
import com.mubarak.democleanarchitecture.kotlinfetured.featured.home.data.model.HomeDataClass
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HomePageUseCase @Inject constructor(private val homeRepo: HomeRepo) {

    operator fun invoke(): Flow<NetworkResponse<List<HomeDataClass>>> = flow {
        try {
            emit(NetworkResponse.Loading)
            val response = homeRepo.getHomePageData()
            Log.e("TAG", "invoke: Response $response")
            emit(NetworkResponse.Success(response))
        } catch (e: Exception) {
            Log.e("TAG", "invoke: Error $e")
        }
    }
}