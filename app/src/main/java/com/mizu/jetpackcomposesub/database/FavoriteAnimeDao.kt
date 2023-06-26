package com.mizu.jetpackcomposesub.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface FavoriteAnimeDao {
    @Insert
    suspend fun addToFavorite(favoriteAnime: FavoriteAnime)

    @Query("SELECT * FROM favorite_anime")
    fun getFavoriteAnime(): LiveData<List<FavoriteAnime>>

    @Query("SELECT count(*) FROM favorite_anime WHERE favorite_anime.id = :id")
    fun checkFavorite(id: Int): Int

    @Query("DELETE FROM favorite_anime WHERE favorite_anime.id = :id")
    suspend fun removeFavorite(id: Int): Unit

}