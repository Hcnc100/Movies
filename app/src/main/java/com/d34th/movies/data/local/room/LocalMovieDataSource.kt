package com.d34th.movies.data.local.room

import com.d34th.movies.data.models.MovieEntity
import com.d34th.movies.data.models.MovieList
import com.d34th.movies.utils.Constants

class LocalMovieDataSource(private val movieDao: MovieDao) {

    suspend fun getUpcomingMovies(): List<MovieEntity> {
        return movieDao.getAllMovies().filter { it.movieType == "upcoming" }
    }

    suspend fun getTopRateMovies(): List<MovieEntity>{
        return movieDao.getAllMovies().filter { it.movieType == "toprated" }
    }

    suspend fun getPopularMovies(): List<MovieEntity>{
        return movieDao.getAllMovies().filter { it.movieType == "popular" }
    }

    suspend fun saveMovie(movie: MovieEntity){
        movieDao.saveMovie(movie)
    }

    suspend fun saveListMovies(movies:List<MovieEntity>){
        movieDao.saveListMovies(movies)
    }
}