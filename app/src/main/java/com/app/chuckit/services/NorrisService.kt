package com.app.chuckit.services

import com.app.chuckit.models.ChuckNorrisFactsResultByQuery
import retrofit2.http.GET
import retrofit2.http.Query

interface NorrisService {

    @GET("categories/")
    suspend fun getCategories(): List<String>

    @GET("search/")
    suspend fun searchChuckNorrisFactsWithQuery(@Query("query") query: String): ChuckNorrisFactsResultByQuery
}