package com.app.chuckit.db.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.app.chuckit.db.entities.CategoryEntity
import com.app.chuckit.db.entities.NorrisFactsEntity
import com.app.chuckit.db.entities.SearchHistoryEntity

@Dao
interface NorrisDao {

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // ChuckNorrisFactsEntity

    @Query("SELECT * FROM chuck_norris_facts")
    suspend fun selectAllNorrisFacts(): List<NorrisFactsEntity>

    @Insert(onConflict = REPLACE, entity = NorrisFactsEntity::class)
    suspend fun insertNorrisFact(vararg norrisFactsEntity: NorrisFactsEntity)

    @Delete
    suspend fun deleteNorrisFact(norrisFactsEntity: NorrisFactsEntity)

    @Query("DELETE FROM chuck_norris_facts")
    suspend fun deleteAllFromNorrisFact()

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // CategoriesEntity

    @Insert(onConflict = REPLACE, entity = CategoryEntity::class)
    suspend fun insertCategories(vararg categoryEntities: CategoryEntity)

    @Query("SELECT * FROM CategoryEntity")
    suspend fun selectAllCategories(): List<CategoryEntity>

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // SearchHistoryEntity

    @Insert(onConflict = REPLACE, entity = SearchHistoryEntity::class)
    suspend fun insertSearchHistory(vararg searchHistoryEntity: SearchHistoryEntity)

    @Query("SELECT * FROM SearchHistoryEntity")
    suspend fun selectAllSearchHistory(): List<SearchHistoryEntity>
}