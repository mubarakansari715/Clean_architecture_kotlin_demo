package com.mubarak.democleanarchitecture.kotlinfetured.featured.home.presentation.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.mubarak.democleanarchitecture.databinding.ActivityMainBinding
import com.mubarak.democleanarchitecture.kotlinfetured.featured.home.presentation.adapter.HomeAdapter
import com.mubarak.democleanarchitecture.kotlinfetured.featured.home.presentation.viewmodel.HomePageResponseState
import com.mubarak.democleanarchitecture.kotlinfetured.featured.home.presentation.viewmodel.HomeRepoViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val homeRepoViewModel: HomeRepoViewModel by viewModels()
    private lateinit var adapter: HomeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        homeRepoViewModel.fetchHomePageResponse()

        lifecycleScope.launch {
            homeRepoViewModel.myHomeResponse.collect { result ->
                when (result) {
                    is HomePageResponseState.Loading -> {
                        Log.d("TAG", "onCreate: @@@ => Loading...")
                    }

                    is HomePageResponseState.Error -> {
                        Log.d("TAG", "onCreate: @@@ => Error!")
                    }

                    is HomePageResponseState.Success -> {
                        Log.d("TAG", "onCreate: @@@ => Success Data => ${result.response}")

                        adapter = HomeAdapter(result.response)
                        binding.rvHomePage.adapter = adapter
                    }
                }
            }
        }
    }
}