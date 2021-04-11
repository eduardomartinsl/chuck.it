package com.app.chuckit.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.app.chuckit.db.dao.NorrisDao
import com.app.chuckit.db.entities.CategoryEntity
import com.app.chuckit.db.entities.NorrisFactsEntity
import com.app.chuckit.db.entities.SearchHistoryEntity
import com.app.chuckit.utils.Converters

@Database(
    entities = [
        NorrisFactsEntity::class,
        CategoryEntity::class,
        SearchHistoryEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class NorrisDatabase : RoomDatabase() {
    abstract fun norrisDao(): NorrisDao
}