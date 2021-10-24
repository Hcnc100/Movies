package com.d34th.movies.ui.adapters.concat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.d34th.movies.databinding.PopularMoviesRowBinding
import com.d34th.movies.databinding.UpcomingMoviesRowBinding
import com.d34th.movies.ui.adapters.MovieAdapter

class UpcomingMoviesAdapter(
    private val moviesAdapter: MovieAdapter
) : RecyclerView.Adapter<UpcomingMoviesAdapter.ConcatViewHolder>() {


    inner class ConcatViewHolder(item: UpcomingMoviesRowBinding) :
        RecyclerView.ViewHolder(item.root) {
        val recycler = item.recycler
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConcatViewHolder {
        return ConcatViewHolder(
            UpcomingMoviesRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int = 1


    override fun onBindViewHolder(holder: ConcatViewHolder, position: Int) {
        holder.recycler.adapter=moviesAdapter
    }
}