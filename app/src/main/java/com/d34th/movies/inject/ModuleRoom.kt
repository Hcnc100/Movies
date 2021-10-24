package com.d34th.movies.inject

import android.content.Context
import androidx.room.Room
import com.d34th.movies.data.local.room.AppDatabase
import com.d34th.movies.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ModuleRoom {
    @Provides
    @Singleton
    fun providerMoviesDataBase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        AppDatabase::class.java,
        Constants.DATABASE_NAME
    ).build()

    @Provides
    @Singleton
    fun provideRunDAO(database: AppDatabase) = database.movieDao()
}