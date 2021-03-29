package com.app.chuckit.repository.interfaces

import com.app.chuckit.models.ChuckNorrisFact

interface BaseNorrisRepository {

    suspend fun getAllCategories(): List<String>

    suspend fun searchChuckNorrisFactsWithQuery(query: String): List<ChuckNorrisFact>

    suspend fun saveSearchSugestion(searchSugestionStr: String)

    suspend fun loadSearchSugestions(): List<String>
}