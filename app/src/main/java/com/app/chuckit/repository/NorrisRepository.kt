package com.app.chuckit.repository

import android.util.Log
import com.app.chuckit.db.dao.NorrisDao
import com.app.chuckit.db.entities.CategoryEntity
import com.app.chuckit.db.entities.ChuckNorrisFactsEntity
import com.app.chuckit.db.entities.SearchSugestionEntity
import com.app.chuckit.models.ChuckNorrisFact
import com.app.chuckit.repository.interfaces.BaseNorrisRepository
import com.app.chuckit.services.NorrisService
import javax.inject.Inject

class NorrisRepository @Inject constructor(
    private val norrisService: NorrisService,
    private val norrisDao: NorrisDao
) : BaseNorrisRepository {

    ////////////////////////////////////////////////////////////////////////////////////////////////

    override suspend fun searchChuckNorrisFactsWithQuery(query: String) {
        try {
            val chuckNorrisFactsResultByQuery = norrisService.searchChuckNorrisFactsWithQuery(query)
            saveChuckNorrisFacts(chuckNorrisFactsResultByQuery.chuckNorrisFacts)
        } catch (e: Exception) {
            //TODO: tratar erro aqui
            Log.e("search", e.toString())
        }
    }

    private suspend fun saveChuckNorrisFacts(chuckNorrisFacts: List<ChuckNorrisFact>) {
        try {
            norrisDao.deleteAllFromChuckNorrisFact()
            for (chuckNorrisFact in chuckNorrisFacts)
                norrisDao.insertChuckNorrisFact(
                    ChuckNorrisFactsEntity(
                        id = chuckNorrisFact.id,
                        categories = chuckNorrisFact.categories,
                        createdAt = chuckNorrisFact.createdAt,
                        iconURL = chuckNorrisFact.iconURL,
                        updatedAt = chuckNorrisFact.updatedAt,
                        url = chuckNorrisFact.url,
                        value = chuckNorrisFact.value
                    )
                )
        } catch (e: Exception) {
            //TODO: tratar erro aqui
        }
    }

    override suspend fun getAllNorrisFacts(): List<ChuckNorrisFact> {
        return norrisDao.selectAllChuckNorrisFacts().map {
            ChuckNorrisFact(
                id = it.id,
                categories = it.categories,
                createdAt = it.createdAt,
                iconURL = it.iconURL,
                updatedAt = it.updatedAt,
                url = it.url,
                value = it.value
            )
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    override suspend fun getAllCategories(): List<String> {

        val categories = norrisDao.selectAllCategories().map {
            it.value
        }

        if (categories.isNotEmpty())
            return categories

        try {
            return norrisService.getCategories().also {
                for (category in it)
                    norrisDao.insertCategories(CategoryEntity(value = category))
            }
        } catch (e: Exception) {
            //TODO: tratar erro aqui
            Log.e("getAllCategories", e.toString())
        }

        return emptyList()
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    override suspend fun saveSearchSugestion(searchSugestionStr: String) {
        norrisDao.insertSearchSugestion(SearchSugestionEntity(value = searchSugestionStr))
    }

    override suspend fun loadSearchSugestions(): List<String> {
        return norrisDao.selectAllSearchSugestions().map {
            it.value
        }
    }
}
