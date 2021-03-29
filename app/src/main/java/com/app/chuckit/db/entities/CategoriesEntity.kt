package com.app.chuckit.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class CategoriesEntity(

    // TODO = testar autogenerate do id
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val value: String

)