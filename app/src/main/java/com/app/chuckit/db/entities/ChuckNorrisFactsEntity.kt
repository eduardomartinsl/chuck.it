package com.app.chuckit.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "chuck_norris_facts")
data class ChuckNorrisFactsEntity(

    @PrimaryKey(autoGenerate = false)
    val id: String,

    val categories: List<String>,

    val createdAt: String,

    val iconURL: String,

    val updatedAt: String,

    val url: String,

    val value: String

)