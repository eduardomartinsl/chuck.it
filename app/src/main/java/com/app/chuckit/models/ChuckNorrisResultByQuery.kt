package com.app.chuckit.models

import com.google.gson.annotations.SerializedName

data class ChuckNorrisResultByQuery(
    @SerializedName("total")
    val total: Int,

    @SerializedName("ChuckNorrisFacts")
    val chuckNorrisFacts: List<ChuckNorrisFact>
)
