package com.app.chuckit.repository

import android.util.Log
import com.app.chuckit.db.dao.NorrisDao
import com.app.chuckit.db.entities.CategoryEntity
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

        val categories = norrisDao.selectAllCategories().map {
            it.value
        }

        if (categories.isNotEmpty()) {
            return categories
        } else {
            try {
                return norrisService.getCategories().also {
                    for (category in it)
                        norrisDao.insertCategories(CategoryEntity(value = category))
                }
            } catch (e: Exception) {
                Log.e("getAllCategories", e.toString())
            }
        }
        return emptyList()
    }

    override suspend fun searchChuckNorrisFactsWithQuery(query: String): List<ChuckNorrisFact> {
        try {
            val chuckNorrisFactsResultByQuery = norrisService.searchChuckNorrisFactsWithQuery(query)
            return chuckNorrisFactsResultByQuery.chuckNorrisFacts
        } catch (e: Exception) {
            Log.e("search", e.toString())
        } finally {
        }
        return emptyList()
    }

    override suspend fun saveSearchSugestion(searchSugestionStr: String) {
        norrisDao.insertSearchSugestion(SearchSugestionEntity(value = searchSugestionStr))
    }

    override suspend fun loadSearchSugestions(): List<String> {
        return norrisDao.selectAllSearchSugestions().map {
            it.value
        }
    }
}
