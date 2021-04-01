package com.app.chuckit.repository.interfaces

import com.app.chuckit.models.ChuckNorrisFact

interface BaseNorrisRepository {

    ////////////////////////////////////////////////////////////////////////////////////////////////

    suspend fun searchChuckNorrisFactsWithQuery(query: String)

    suspend fun getAllNorrisFacts() : List<ChuckNorrisFact>

    ////////////////////////////////////////////////////////////////////////////////////////////////

    suspend fun getAllCategories(): List<String>

    ////////////////////////////////////////////////////////////////////////////////////////////////

    suspend fun saveSearchSugestion(searchSugestionStr: String)

    suspend fun loadSearchSugestions(): List<String>
}