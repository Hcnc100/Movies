package com.d34th.movies.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.d34th.movies.data.models.MovieEntity
import com.d34th.movies.databinding.ItemMovieBinding
import com.d34th.movies.utils.Constants.PREFIX_IMG

class MovieAdapter(
    private val clickMovie: OnMovieClickListener
) : ListAdapter<MovieEntity, MovieAdapter.MoviesViewHolder>(DiffUtilMovies) {

    interface OnMovieClickListener {
        fun onMovieClick(movie: MovieEntity)
        fun updateImgMovie(movie: MovieEntity)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        return MoviesViewHolder(
            ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.apply {
            getItem(position).let { movie ->
                if(movie.bitmapPoster==null){
                    image.load("$PREFIX_IMG${movie.urlImagePoster}")
                }else{
                    image.load(movie.bitmapPoster)
                }
                container.setOnClickListener {
                    clickMovie.onMovieClick(movie)
                }
            }
        }
    }

    inner class MoviesViewHolder(
        binding: ItemMovieBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        val image = binding.imageMovie
        val container = binding.container
    }

    private object DiffUtilMovies:DiffUtil.ItemCallback<MovieEntity>(){
        override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean =
            oldItem.id==newItem.id

        override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean =
            oldItem==newItem

    }
}