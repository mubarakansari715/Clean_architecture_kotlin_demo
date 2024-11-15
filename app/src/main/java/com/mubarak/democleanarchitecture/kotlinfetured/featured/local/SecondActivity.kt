package com.mubarak.democleanarchitecture.kotlinfetured.featured.local

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.mubarak.democleanarchitecture.databinding.ActivitySecondBinding
import com.mubarak.democleanarchitecture.kotlinfetured.featured.home.presentation.adapter.HomeAdapter
import com.mubarak.democleanarchitecture.kotlinfetured.featured.home.presentation.viewmodel.HomePageResponseState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding
    private val viewModel: LocalViewModel by viewModels()
    private lateinit var adapter: HomeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.loadJSONFromAsset()

        lifecycleScope.launch {
            viewModel.localJsonLoad.collect { result ->
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