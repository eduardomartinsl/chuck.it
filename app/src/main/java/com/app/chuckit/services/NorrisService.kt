package com.app.chuckit.services

import com.app.chuckit.models.ChuckNorrisFactsResultByQuery
import retrofit2.http.GET
import retrofit2.http.Path

interface NorrisService {

    @GET("categories/")
    suspend fun getCategories(): List<String>

    @GET("search?query={query}")
    suspend fun searchChuckNorrisFactsWithQuery(@Path("query") query: String): ChuckNorrisFactsResultByQuery
}