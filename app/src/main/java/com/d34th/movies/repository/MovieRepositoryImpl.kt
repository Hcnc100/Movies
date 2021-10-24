package com.d34th.movies.repository

import android.content.Context
import android.graphics.Bitmap
import coil.ImageLoader
import com.d34th.movies.data.local.room.LocalMovieDataSource
import com.d34th.movies.data.models.Movie
import com.d34th.movies.data.models.MovieEntity
import com.d34th.movies.data.remote.RemoteMoviesDataSource
import com.d34th.movies.utils.InternetCheck

class MovieRepositoryImpl(
    private val remoteMoviesDataSource: RemoteMoviesDataSource,
    private val localMovieDataSource: LocalMovieDataSource,
) : MovieRepository {

    override suspend fun getUpcomingMovies(): List<MovieEntity>{
        if(InternetCheck.isNetworkAvailable()){
            val list=remoteMoviesDataSource.getUpcomingMovies().results
            localMovieDataSource.saveListMovies(list.map {
                Movie.toMovieEntity(it,"upcoming")
            })
        }
        return localMovieDataSource.getUpcomingMovies()
    }

    override suspend fun getTopRateMovies(): List<MovieEntity>{
        if(InternetCheck.isNetworkAvailable()) {
            val list = remoteMoviesDataSource.getTopRateMovies().results
            localMovieDataSource.saveListMovies(list.map {
                Movie.toMovieEntity(it,"toprated")
            })
        }
        return localMovieDataSource.getTopRateMovies()
    }

    override suspend fun getPopularMovies(): List<MovieEntity>{
        if(InternetCheck.isNetworkAvailable()) {
            val list = remoteMoviesDataSource.getPopularMovies().results
            localMovieDataSource.saveListMovies(list.map {
                Movie.toMovieEntity(it,"popular")
            })
        }
        return localMovieDataSource.getPopularMovies()
    }

    override suspend fun updateMovie(movieEntity: MovieEntity) =
        localMovieDataSource.saveMovie(movieEntity)


}