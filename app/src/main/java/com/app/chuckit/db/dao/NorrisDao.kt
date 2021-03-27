package com.app.chuckit.db.dao

import androidx.room.*
import com.app.chuckit.db.entities.ChuckNorrisFactsEntity

@Dao
interface NorrisDao  {
    @Query("SELECT * FROM chuck_norris_facts")
    suspend fun selectAllChuckNorrisFacts() : List<ChuckNorrisFactsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChuckNorrisFact(ChuckNorrisFactsEntity: ChuckNorrisFactsEntity)

    @Delete
    suspend fun deleteChuckNorrisFact(ChuckNorrisFactsEntity: ChuckNorrisFactsEntity)
}