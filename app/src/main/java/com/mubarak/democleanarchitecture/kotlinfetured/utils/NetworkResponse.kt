package com.mubarak.democleanarchitecture.kotlinfetured.utils

sealed class NetworkResponse<out T : Any?> {
    object Loading : NetworkResponse<Nothing>()
    data class Error(val errorMessage: String, val errorDetails: Any?= null) : NetworkResponse<Nothing>()
    data class Success<out T : Any>(val result: T?) : NetworkResponse<T>()
}