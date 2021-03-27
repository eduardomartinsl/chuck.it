package com.app.chuckit.repository.interfaces

import com.app.chuckit.models.ChuckNorrisFact
import com.app.chuckit.models.ChuckNorrisResultByQuery
import com.app.chuckit.utils.Resource

interface BaseNorrisRepository {

    suspend fun getRandomJoke(): Resource<ChuckNorrisFact>

    suspend fun getRandomJokeByCategory(category: String) : Resource<ChuckNorrisFact>

    suspend fun getJokeCategories() : Resource<List<String>>

    suspend fun getJokesWithQhery(query: String) : Resource<ChuckNorrisResultByQuery>

    suspend fun selectAllChuckNorrisFacts()
}