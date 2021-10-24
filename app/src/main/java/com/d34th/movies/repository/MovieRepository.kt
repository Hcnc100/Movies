package com.d34th.movies.repository

import com.d34th.movies.data.models.MovieEntity

interface MovieRepository {

    suspend fun getUpcomingMovies():List<MovieEntity>

    suspend fun getTopRateMovies():List<MovieEntity>

    suspend fun getPopularMovies():List<MovieEntity>

    suspend fun updateMovie(movieEntity: MovieEntity)

}