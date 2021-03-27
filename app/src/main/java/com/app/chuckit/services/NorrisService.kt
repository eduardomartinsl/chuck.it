package com.app.chuckit.services

import com.app.chuckit.models.ChuckNorrisFact
import com.app.chuckit.models.ChuckNorrisResultByQuery
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface NorrisService {

    @GET("random/")
    suspend fun getRandomJoke(): Response<ChuckNorrisFact>

    @GET("random?category={category}/")
    suspend fun getRandomJokeByCategory(@Path("category") category: String): Response<ChuckNorrisFact>

    @GET("categories/")
    suspend fun getJokeCategories(): Response<List<String>>

    @GET("search?query={query}")
    suspend fun getJokesWithQhery(@Path("query") query: String): Response<ChuckNorrisResultByQuery>
}