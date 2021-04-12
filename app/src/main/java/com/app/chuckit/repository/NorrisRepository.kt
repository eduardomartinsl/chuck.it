package com.app.chuckit.repository

import com.app.chuckit.db.dao.NorrisDao
import com.app.chuckit.db.entities.CategoryEntity
import com.app.chuckit.db.entities.NorrisFactsEntity
import com.app.chuckit.db.entities.SearchHistoryEntity
import com.app.chuckit.models.NorrisFact
import com.app.chuckit.repository.interfaces.BaseNorrisRepository
import com.app.chuckit.services.NorrisService
import com.app.chuckit.utils.CategoriesHelper
import javax.inject.Inject

class NorrisRepository @Inject constructor(
    private val norrisService: NorrisService,
    private val norrisDao: NorrisDao
) : BaseNorrisRepository {

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // NorrisFacts

    override suspend fun searchNorrisFactsWithQuery(query: String): List<NorrisFact> {
        val result = norrisService.searchNorrisFactsWithQuery(query)
        saveNorrisFacts(result.norrisFacts)
        return result.norrisFacts
    }

    private suspend fun saveNorrisFacts(norrisFacts: List<NorrisFact>) {
        norrisDao.deleteAllFromNorrisFact()
        for (NorrisFact in norrisFacts)
            norrisDao.insertNorrisFact(
                NorrisFactsEntity(
                    id = NorrisFact.id,
                    categories = NorrisFact.categories,
                    createdAt = NorrisFact.createdAt,
                    iconURL = NorrisFact.iconURL,
                    updatedAt = NorrisFact.updatedAt,
                    url = NorrisFact.url,
                    value = NorrisFact.value
                )
            )
    }

    override suspend fun getAllNorrisFacts(): List<NorrisFact> {
        return norrisDao.selectAllNorrisFacts().map {
            NorrisFact(
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
    // Categories

    override suspend fun getCategories(): List<String> {

        val categories = norrisDao.selectAllCategories().map {
            it.value
        }

        if (categories.isNotEmpty()) {
            return CategoriesHelper.shuffleAndTakeFirstEightElements(categories)
        }

        norrisService.getCategories().also { categoriesFromService ->

            for (category in categoriesFromService) {
                norrisDao.insertCategories(CategoryEntity(value = category))
            }

            return CategoriesHelper.shuffleAndTakeFirstEightElements(categoriesFromService)
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // SearchSugestion

    override suspend fun saveSearchHistory(searchStr: String) {
        if (norrisDao.selectSearchHistory(searchStr).isEmpty())
            norrisDao.insertSearchHistory(SearchHistoryEntity(value = searchStr))
    }

    override suspend fun loadSearchHistory(): List<String> {
        return norrisDao.selectAllSearchHistory().map {
            it.value
        }
    }
}
