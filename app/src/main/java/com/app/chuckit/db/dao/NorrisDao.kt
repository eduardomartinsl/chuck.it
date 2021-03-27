package com.app.chuckit.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.app.chuckit.models.ChuckNorrisFact

@Dao
interface NorrisDao {
    @Query("SELECT * FROM ChuckNorrisFactsEntity")
    suspend fun selectAllChuckNorrisFacts() : List<ChuckNorrisFact>
}