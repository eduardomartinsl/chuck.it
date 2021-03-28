package com.app.chuckit.repository

import android.util.Log
import com.app.chuckit.db.dao.NorrisDao
import com.app.chuckit.db.entities.CategoriesEntity
import com.app.chuckit.models.ChuckNorrisFact
import com.app.chuckit.services.NorrisService
import java.lang.Exception
import javax.inject.Inject

class NorrisRepository @Inject constructor(
    private val norrisService: NorrisService,
    private val norrisDao: NorrisDao
) {

    suspend fun getAllCategories() : List<String> {
        var categories = norrisDao.selectAllCategories()

        if(categories.isEmpty()){
            try {
                categories = norrisService.getCategories().also {

                    // TODO: Preciso melhorar essa maneira de salvar as categorias
//                    for (category in it)
//                        norrisDao.saveCategories(CategoriesEntity(value = category))
                }
            }catch (exception: Exception){
                Log.e("CategoriesException", "${exception.stackTrace}")
                return emptyList()
            }
        }
        return categories
    }

    suspend fun searchChuckNorrisFactsWithQuery(query: String) : List<ChuckNorrisFact> {
        val chuckNorrisFactsResultByQuery = norrisService.searchChuckNorrisFactsWithQuery(query)
        return chuckNorrisFactsResultByQuery.chuckNorrisFacts

    }

    suspend fun selectAllChuckNorrisFactsFromPersistence() = norrisDao.selectAllChuckNorrisFacts()

    suspend fun saveSearchSugestion(searchSugestion: String) {
//        norrisDao.saveSearchSugestion(searchSugestion)
    }

    suspend fun loadSearchSugestions(): List<String> {
        return norrisDao.selectAllSearchSugestions()
    }
}