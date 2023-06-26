package com.mizu.jetpackcomposesub.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("anime/ranking?ranking_type=airing&limit=100")
    @Headers("X-MAL-CLIENT-ID: 1e4c65bbf274b0e8e3529f82bb4c19d6")
    suspend fun getAnimeList() : Response

    @GET("anime")
    @Headers("X-MAL-CLIENT-ID: 1e4c65bbf274b0e8e3529f82bb4c19d6")
    suspend fun searchAnime(
        @Query("q") query: String,
        @Query("limit") limit: Int = 100,
    ) : Response

    @GET("anime/{id}?fields=id,title,main_picture,alternative_titles,synopsis,media_type,status,genres,num_episodes,start_season,rating,pictures,studios")
    @Headers("X-MAL-CLIENT-ID: 1e4c65bbf274b0e8e3529f82bb4c19d6")
    suspend fun getAnimeDetail(
        @Path("id") id : Int
    ) : DetailResponse

    companion object{
        private var apiService : ApiService? = null
        fun getInstance() : ApiService {
            if (apiService == null){
                apiService = Retrofit.Builder()
                    .baseUrl("https://api.myanimelist.net/v2/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(ApiService::class.java)
            }
            return apiService!!
        }
    }
}