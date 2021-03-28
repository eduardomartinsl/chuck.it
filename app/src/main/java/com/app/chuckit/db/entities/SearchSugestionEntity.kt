package com.app.chuckit.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SearchSugestionEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int,

    val value: String
)