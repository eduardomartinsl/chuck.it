package com.app.chuckit.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.app.chuckit.db.dao.NorrisDao
import com.app.chuckit.db.entities.ChuckNorrisFactsEntity
import com.app.chuckit.utils.Converters

@Database(entities = [ChuckNorrisFactsEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun norrisDao(): NorrisDao
}