package com.app.chuckit.models

data class ChuckNorrisResultByQuery(
    val total: Int,
    val ChuckNorrisFacts: List<ChuckNorrisFact>
)
