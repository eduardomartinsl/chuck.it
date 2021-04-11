package com.app.chuckit.models

import com.google.gson.annotations.SerializedName

data class NorrisFactsResultByQuery(
    @SerializedName("total")
    val total: Int,

    @SerializedName("result")
    val norrisFacts: List<NorrisFact>
)
