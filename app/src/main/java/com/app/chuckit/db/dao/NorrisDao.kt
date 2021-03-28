package com.app.chuckit.db.dao

import androidx.room.Dao
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import com.app.chuckit.db.entities.CategoriesEntity
import com.app.chuckit.db.entities.SearchSugestionEntity
import com.app.chuckit.models.ChuckNorrisFact

@Dao
interface NorrisDao {

    // ChuckNorrisFactsEntity

    @Query("SELECT * FROM ChuckNorrisFactsEntity")
    suspend fun selectAllChuckNorrisFacts() : List<ChuckNorrisFact>

    // CategoriesEntity

    // TODO: CORRIGIR ISSO
    //  @Update(onConflict = REPLACE, entity = CategoriesEntity::class)
    //  suspend fun saveCategories(vararg categoriesEntity: CategoriesEntity)

    @Query("SELECT value FROM CategoriesEntity")
    suspend fun selectAllCategories() : List<String>

    // SearchSugestionEntity

    // TODO: CORRIGIR ISSO
    //  @Update(onConflict = REPLACE, entity = SearchSugestionEntity::class)
    //  suspend fun saveSearchSugestion(sugestion: String)

    @Query("SELECT value FROM searchsugestionentity")
    suspend fun selectAllSearchSugestions() : List<String>
}