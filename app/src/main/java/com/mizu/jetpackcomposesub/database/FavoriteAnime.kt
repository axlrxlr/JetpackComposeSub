package com.mizu.jetpackcomposesub.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_anime")
data class FavoriteAnime(
    @PrimaryKey
    val id: Int,

    val title: String,

    val imageUrl: String,
)
