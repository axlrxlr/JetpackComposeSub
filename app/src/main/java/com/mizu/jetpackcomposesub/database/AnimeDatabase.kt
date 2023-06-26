package com.mizu.jetpackcomposesub.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [FavoriteAnime::class],
    version = 1
)

abstract class AnimeDatabase: RoomDatabase() {
    companion object{
        var INSTANCE : AnimeDatabase? = null

        fun getDatabase(context:Context): AnimeDatabase?{
            if (INSTANCE==null){
                synchronized(AnimeDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext, AnimeDatabase::class.java, "anime_database").build()
                }
            }
            return INSTANCE
        }
    }

    abstract fun favoriteAnimeDao(): FavoriteAnimeDao
}
