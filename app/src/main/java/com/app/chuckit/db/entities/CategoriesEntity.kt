package com.app.chuckit.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class CategoriesEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int,

    val value: String

)