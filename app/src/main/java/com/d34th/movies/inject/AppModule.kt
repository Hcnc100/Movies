package com.d34th.movies.inject

import android.content.Context
import androidx.room.Room
import coil.ImageLoader
import coil.imageLoader
import coil.request.ImageRequest
import com.d34th.movies.data.local.room.AppDatabase
import com.d34th.movies.data.local.room.LocalMovieDataSource
import com.d34th.movies.data.local.room.MovieDao
import com.d34th.movies.data.remote.RemoteMoviesDataSource
import com.d34th.movies.repository.MovieRepository
import com.d34th.movies.repository.MovieRepositoryImpl
import com.d34th.movies.repository.WebServices
import com.d34th.movies.utils.Constants.BASE_URL
import com.d34th.movies.utils.Constants.DATABASE_NAME
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideWebServices(retrofit: Retrofit): WebServices =
        retrofit.create(WebServices::class.java)

    @Provides
    @Singleton
    fun provideRemoteDataSource(webServices: WebServices): RemoteMoviesDataSource =
        RemoteMoviesDataSource(webServices)

    @Provides
    @Singleton
    fun provideLocalDataSource(movieDao: MovieDao): LocalMovieDataSource =
        LocalMovieDataSource(movieDao)

    @Provides
    @Singleton
    fun provideImageLoader(
        @ApplicationContext context: Context
    )=ImageLoader(context)

    @Singleton
    @Provides
    fun provideMoviesRepository(
        remoteMoviesDataSource: RemoteMoviesDataSource,
        localMovieDataSource: LocalMovieDataSource,
    ): MovieRepository =
        MovieRepositoryImpl(remoteMoviesDataSource, localMovieDataSource)

}