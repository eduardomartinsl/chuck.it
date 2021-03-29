package com.app.chuckit.repository

import android.util.Log
import com.app.chuckit.db.dao.NorrisDao
import com.app.chuckit.db.entities.CategoriesEntity
import com.app.chuckit.db.entities.SearchSugestionEntity
import com.app.chuckit.models.ChuckNorrisFact
import com.app.chuckit.repository.interfaces.BaseNorrisRepository
import com.app.chuckit.services.NorrisService
import javax.inject.Inject

class NorrisRepository @Inject constructor(
    private val norrisService: NorrisService,
    private val norrisDao: NorrisDao
) : BaseNorrisRepository {

    override suspend fun getAllCategories(): List<String> {
        var categories = norrisDao.selectAllCategories()

        if (categories.isEmpty()) {
            try {
                categories = norrisService.getCategories().also {

                    for (category in it)
                        norrisDao.saveCategories(CategoriesEntity(value = category))
                }
            } catch (exception: Exception) {
                Log.e("CategoriesException", "${exception.stackTrace}")
                return emptyList()
            }
        }
        return categories
    }

    override suspend fun searchChuckNorrisFactsWithQuery(query: String): List<ChuckNorrisFact> {
        val chuckNorrisFactsResultByQuery = norrisService.searchChuckNorrisFactsWithQuery(query)
        return chuckNorrisFactsResultByQuery.chuckNorrisFacts

    }

    override suspend fun saveSearchSugestion(searchSugestionStr: String) {
        //norrisDao.saveCategories(CategoriesEntity(value = category))
        norrisDao.saveSearchSugestion(SearchSugestionEntity(value = searchSugestionStr))
    }

    override suspend fun loadSearchSugestions(): List<String> {
        return norrisDao.selectAllSearchSugestions()
    }
}