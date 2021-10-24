package com.d34th.movies.ui.adapters.concat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.d34th.movies.databinding.PopularMoviesRowBinding
import com.d34th.movies.ui.adapters.MovieAdapter

class PopularConcatAdapter(
    private val moviesAdapter: MovieAdapter
) : RecyclerView.Adapter<PopularConcatAdapter.ConcatViewHolder>() {


    inner class ConcatViewHolder(item: PopularMoviesRowBinding) :
        RecyclerView.ViewHolder(item.root) {
        val recycler = item.recycler
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConcatViewHolder {
        return ConcatViewHolder(
            PopularMoviesRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ConcatViewHolder, position: Int) {
        holder.recycler.adapter=moviesAdapter
    }

    override fun getItemCount(): Int = 1
}