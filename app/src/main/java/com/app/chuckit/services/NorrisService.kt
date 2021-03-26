package com.app.chuckit.services

import com.app.chuckit.models.ChuckNorrisFact
import com.app.chuckit.models.ChuckNorrisResultByQuery
import retrofit2.http.GET
import retrofit2.http.Path

interface NorrisService {

    @GET("random/")
    suspend fun getRandomJoke(): ChuckNorrisFact

    @GET("random?category={category}/")
    suspend fun getRandomJokeByCategory(@Path("category") category: String): ChuckNorrisFact

    @GET("categories/")
    suspend fun getJokeCategories(): List<String>

    @GET("search?query={query}")
    suspend fun searchJokesByQuery(@Path("query") query: String): ChuckNorrisResultByQuery
}