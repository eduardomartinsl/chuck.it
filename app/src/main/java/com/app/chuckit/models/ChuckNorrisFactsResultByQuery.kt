package com.app.chuckit.models

import com.google.gson.annotations.SerializedName

data class ChuckNorrisFactsResultByQuery(
    @SerializedName("total")
    val total: Int,

    @SerializedName("result")
    val chuckNorrisFacts: List<ChuckNorrisFact>
)
