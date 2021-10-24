package com.d34th.movies.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.d34th.movies.data.models.MovieEntity

@Database(
    entities = [MovieEntity::class],
    version = 1)
@TypeConverters(Converts::class)
abstract class AppDatabase :RoomDatabase(){
    abstract fun movieDao(): MovieDao
}