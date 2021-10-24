package com.d34th.movies.repository

import com.d34th.movies.data.models.MovieList
import com.d34th.movies.utils.Constants.BASE_URL
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WebServices {

    @GET("upcoming")
    suspend fun getUpcomingMovies(@Query("api_key") api_key: String): MovieList

    @GET("top_rated")
    suspend fun getTopRateMovies(@Query("api_key") api_key: String): MovieList

    @GET("popular")
    suspend fun getPopularMovies(@Query("api_key") api_key: String): MovieList
}
