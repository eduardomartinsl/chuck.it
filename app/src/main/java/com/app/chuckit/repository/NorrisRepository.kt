package com.app.chuckit.repository

import com.app.chuckit.db.dao.NorrisDao
import com.app.chuckit.services.NorrisService

class NorrisRepository(
    private val norrisService: NorrisService,
    private val norrisDao: NorrisDao
) {

    ////////////////////////////////////////////////////////////////////////////////////////////////

    // -- Service
    suspend fun getRandomJoke() = norrisService.getRandomJoke()

    suspend fun getRandomJokeByCategory(category: String) =
        norrisService.getRandomJokeByCategory(category)

    suspend fun getJokeCategories() = norrisService.getJokeCategories()

    suspend fun getJokesWithQhery(query: String) = norrisService.getJokesWithQhery(query)

    ////////////////////////////////////////////////////////////////////////////////////////////////

    // -- DAO
    suspend fun selectAllChuckNorrisFacts() = norrisDao.selectAllChuckNorrisFacts()

}