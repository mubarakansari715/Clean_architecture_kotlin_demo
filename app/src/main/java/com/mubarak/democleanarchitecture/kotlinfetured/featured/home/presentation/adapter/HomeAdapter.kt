package com.mubarak.democleanarchitecture.kotlinfetured.featured.home.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mubarak.democleanarchitecture.databinding.ItemListBinding
import com.mubarak.democleanarchitecture.kotlinfetured.featured.home.data.model.HomeDataClass

class HomeAdapter(val list: List<HomeDataClass>) :
    RecyclerView.Adapter<HomeAdapter.HomeAdapterViewHolder>() {

    class HomeAdapterViewHolder(val binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapterViewHolder {
        return HomeAdapterViewHolder(
            ItemListBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: HomeAdapterViewHolder, position: Int) {
        val data = list[position]

        holder.binding.tvTitle.text = data.title
    }
}