package com.app.chuckit.db.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.app.chuckit.db.entities.CategoriesEntity
import com.app.chuckit.db.entities.ChuckNorrisFactsEntity
import com.app.chuckit.db.entities.SearchSugestionEntity

@Dao
interface NorrisDao {

    // ChuckNorrisFactsEntity

    @Query("SELECT * FROM chuck_norris_facts")
    suspend fun selectAllChuckNorrisFacts(): List<ChuckNorrisFactsEntity>

    @Insert(onConflict = REPLACE, entity = ChuckNorrisFactsEntity::class)
    suspend fun insertChuckNorrisFact(vararg chuckNorrisFactsEntity: ChuckNorrisFactsEntity)

    @Delete
    suspend fun deleteChuckNorrisFact(chuckNorrisFactsEntity: ChuckNorrisFactsEntity)

    // CategoriesEntity

    @Insert(onConflict = REPLACE, entity = CategoriesEntity::class)
    suspend fun saveCategories(vararg categoriesEntities: CategoriesEntity)

    @Query("SELECT value FROM CategoriesEntity")
    suspend fun selectAllCategories(): List<String>

    // SearchSugestionEntity

    @Update(onConflict = REPLACE, entity = SearchSugestionEntity::class)
    suspend fun saveSearchSugestion(searchSugestionEntity: SearchSugestionEntity)

    @Query("SELECT value FROM searchsugestionentity")
    suspend fun selectAllSearchSugestions(): List<String>
}