package com.app.chuckit.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class CategoryEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val value: String

)