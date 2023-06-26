package com.mizu.jetpackcomposesub.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteViewModel(application: Application): AndroidViewModel(application) {

    private var animeDao: FavoriteAnimeDao?
    private var animeDb: AnimeDatabase?


    init{
        animeDb = AnimeDatabase.getDatabase(application)
        animeDao = animeDb?.favoriteAnimeDao()
    }

    fun addToFavorite(id: Int, title: String, imageUrl: String){
        CoroutineScope(Dispatchers.IO).launch {
            val anime = FavoriteAnime(
                id,
                title,
                imageUrl
            )
            animeDao?.addToFavorite(anime)
        }
    }

    fun getFavoriteAnime() : LiveData<List<FavoriteAnime>>? {
        return animeDao?.getFavoriteAnime()
    }

    fun checkFavorite(id: Int) = animeDao?.checkFavorite(id)

    fun removeFavorite(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            animeDao?.removeFavorite(id)
        }
    }
}