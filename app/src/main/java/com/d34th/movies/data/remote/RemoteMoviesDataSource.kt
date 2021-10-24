package com.d34th.movies.data.remote


import com.d34th.movies.data.models.Movie
import com.d34th.movies.data.models.MovieList
import com.d34th.movies.repository.WebServices
import com.d34th.movies.utils.Constants.API_KEY

class RemoteMoviesDataSource(
    private val webServices: WebServices
){
    suspend fun getUpcomingMovies():MovieList= webServices.getUpcomingMovies(API_KEY)

    suspend fun getTopRateMovies():MovieList = webServices.getTopRateMovies(API_KEY)

    suspend fun getPopularMovies():MovieList = webServices.getPopularMovies(API_KEY)
}