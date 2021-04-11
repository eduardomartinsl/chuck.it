package com.app.chuckit.services

import com.app.chuckit.models.NorrisFactsResultByQuery
import retrofit2.http.GET
import retrofit2.http.Query

interface NorrisService {

    @GET("categories/")
    suspend fun getCategories(): List<String>

    @GET("search/")
    suspend fun searchNorrisFactsWithQuery(@Query("query") query: String): NorrisFactsResultByQuery
}