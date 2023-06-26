package com.mizu.jetpackcomposesub.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mizu.jetpackcomposesub.api.AlternativeTitles
import com.mizu.jetpackcomposesub.api.ApiService
import com.mizu.jetpackcomposesub.api.DataItem
import com.mizu.jetpackcomposesub.api.DetailResponse
import com.mizu.jetpackcomposesub.api.MainPicture
import com.mizu.jetpackcomposesub.api.StartSeason
import kotlinx.coroutines.launch
import java.lang.Exception

class AnimeViewModel(): ViewModel() {
    var animeListItem : List<DataItem> by mutableStateOf(listOf())
    var animeDetailItem : DetailResponse by mutableStateOf(emptyDetail)
    var errorMessage: String by mutableStateOf("")

    init {
        getAnimeList()
    }

    fun getAnimeList(){
        viewModelScope.launch {
            val apiService = ApiService.getInstance()
            try {
                val animeList = apiService.getAnimeList()
                animeListItem = animeList.data
            } catch (e: Exception){
                errorMessage = e.message.toString()
            }
        }
    }
    fun searchAnime(query: String){
        viewModelScope.launch {
            val apiService = ApiService.getInstance()
            try {
                val animeList = apiService.searchAnime(query)
                animeListItem = animeList.data
            } catch (e: Exception){
                errorMessage = e.message.toString()
                Log.d("error", errorMessage)
            }
        }
    }
    fun getAnimeDetail(id: Int){
        viewModelScope.launch {
            val apiService = ApiService.getInstance()
            try {
                val animeDetail = apiService.getAnimeDetail(id)
                animeDetailItem = animeDetail
            } catch (e: Exception){
                errorMessage = e.message.toString()
            }
        }
    }
}

private val emptyDetail = DetailResponse(listOf(), "", MainPicture(large = "", medium = ""), "", "", StartSeason(0, ""), listOf(), AlternativeTitles(listOf(),"",""), "", listOf(), 0, 0, "")