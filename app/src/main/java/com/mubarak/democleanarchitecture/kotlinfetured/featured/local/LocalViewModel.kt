package com.mubarak.democleanarchitecture.kotlinfetured.featured.local

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.mubarak.democleanarchitecture.kotlinfetured.featured.home.data.model.HomeDataClass
import com.mubarak.democleanarchitecture.kotlinfetured.featured.home.presentation.viewmodel.HomePageResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class LocalViewModel @Inject constructor(
    @ApplicationContext val mContext: Context
) : ViewModel() {

    private val _localJsonLoad =
        MutableStateFlow<HomePageResponseState>(HomePageResponseState.Loading)
    val localJsonLoad: StateFlow<HomePageResponseState> = _localJsonLoad

    fun loadJSONFromAsset() {
        _localJsonLoad.value = HomePageResponseState.Loading
        val json = mContext.assets.open("data.json").use { inputStream ->
            inputStream.readBytes().toString(Charsets.UTF_8)
        }

        val data = Gson().fromJson(json, Array<HomeDataClass>::class.java).toList().toTypedArray()
        _localJsonLoad.value = HomePageResponseState.Success(data.toMutableList())
    }
}