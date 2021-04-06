package com.app.chuckit.db.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.app.chuckit.db.entities.CategoryEntity
import com.app.chuckit.db.entities.ChuckNorrisFactsEntity
import com.app.chuckit.db.entities.SearchSugestionEntity

@Dao
interface NorrisDao {

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // ChuckNorrisFactsEntity

    @Query("SELECT * FROM chuck_norris_facts")
    suspend fun selectAllChuckNorrisFacts(): List<ChuckNorrisFactsEntity>

    @Insert(onConflict = REPLACE, entity = ChuckNorrisFactsEntity::class)
    suspend fun insertChuckNorrisFact(vararg chuckNorrisFactsEntity: ChuckNorrisFactsEntity)

    @Delete
    suspend fun deleteChuckNorrisFact(chuckNorrisFactsEntity: ChuckNorrisFactsEntity)

    @Query("DELETE FROM chuck_norris_facts")
    suspend fun deleteAllFromChuckNorrisFact()

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // CategoriesEntity

    @Insert(onConflict = REPLACE, entity = CategoryEntity::class)
    suspend fun insertCategories(vararg categoryEntities: CategoryEntity)

    @Query("SELECT * FROM CategoryEntity")
    suspend fun selectAllCategories(): List<CategoryEntity>

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // SearchSugestionEntity

    @Insert(onConflict = REPLACE, entity = SearchSugestionEntity::class)
    suspend fun insertSearchSugestion(vararg searchSugestionEntity: SearchSugestionEntity)

    @Query("SELECT * FROM searchsugestionEntity")
    suspend fun selectAllSearchSugestions(): List<SearchSugestionEntity>
}