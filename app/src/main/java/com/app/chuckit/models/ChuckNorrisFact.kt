package com.app.chuckit.models

import com.google.gson.annotations.SerializedName

data class ChuckNorrisFact(
    @SerializedName("categories")
    val categories: List<String>,

    @SerializedName("created_at")
    val createdAt: String,

    @SerializedName("icon_url")
    val iconURL: String,

    @SerializedName("id")
    val id: String,

    @SerializedName("updated_at")
    val updatedAt: String,

    @SerializedName("url")
    val url: String,

    @SerializedName("value")
    val value: String
)
