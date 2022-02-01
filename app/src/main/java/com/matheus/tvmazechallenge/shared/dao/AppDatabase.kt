package com.matheus.tvmazechallenge.shared.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.matheus.tvmazechallenge.shared.dao.converter.StringListConverter

@Database(
    entities = [TVShowScheduleDAO::class, TVShowDAO::class],
    version = 1
)
@TypeConverters(StringListConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun tvShowDatabaseDao(): TVShowDatabaseDAO
}