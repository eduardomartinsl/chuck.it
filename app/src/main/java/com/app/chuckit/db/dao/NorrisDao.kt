package com.app.chuckit.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import com.app.chuckit.db.entities.CategoriesEntity
import com.app.chuckit.db.entities.ChuckNorrisFactsEntity
import com.app.chuckit.db.entities.SearchSugestionEntity
import com.app.chuckit.models.ChuckNorrisFact

@Dao
interface NorrisDao {

    // ChuckNorrisFactsEntity

    @Query("SELECT * FROM ChuckNorrisFactsEntity")
    suspend fun selectAllChuckNorrisFacts(): List<ChuckNorrisFactsEntity>

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