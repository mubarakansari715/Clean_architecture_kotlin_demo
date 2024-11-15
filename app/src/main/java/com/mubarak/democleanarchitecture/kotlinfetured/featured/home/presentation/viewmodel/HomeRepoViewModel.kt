package com.mubarak.democleanarchitecture.kotlinfetured.featured.home.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mubarak.democleanarchitecture.kotlinfetured.featured.home.domain.usecase.HomePageUseCase
import com.mubarak.democleanarchitecture.kotlinfetured.utils.NetworkResponse
import com.mubarak.democleanarchitecture.kotlinfetured.featured.home.data.model.HomeDataClass
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeRepoViewModel @Inject constructor(
    private val homePageUseCase: HomePageUseCase
) : ViewModel() {

    private val _myHomeResponse =
        MutableStateFlow<HomePageResponseState>(HomePageResponseState.Loading)
    val myHomeResponse: StateFlow<HomePageResponseState> = _myHomeResponse

    fun fetchHomePageResponse() {
        homePageUseCase.invoke().onEach { result ->
            when (result) {
                is NetworkResponse.Loading -> {
                    Log.e("TAG", "fetchHomePageResponse: Loading")
                    _myHomeResponse.value = HomePageResponseState.Loading
                }

                is NetworkResponse.Error -> {
                    Log.e("TAG", "fetchHomePageResponse: Error")
                    _myHomeResponse.value = HomePageResponseState.Error

                }

                is NetworkResponse.Success -> {
                    Log.e("TAG", "fetchHomePageResponse: Success =>>>>> ${result.result}")

                    result.result?.let {
                        _myHomeResponse.value = HomePageResponseState.Success(response = it)
                    }
                }
            }
        }.launchIn(viewModelScope)
    }
}

sealed class HomePageResponseState {
    object Loading : HomePageResponseState()
    object Error : HomePageResponseState()
    class Success(val response: List<HomeDataClass>) : HomePageResponseState()
}