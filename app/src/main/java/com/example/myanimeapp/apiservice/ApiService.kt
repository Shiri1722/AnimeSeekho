package com.example.myanimeapp.apiservice

import com.example.myanimeapp.model.AnimeDetailResponse
import com.example.myanimeapp.model.AnimeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("top/anime")
    fun getTopAnime(): Call<AnimeResponse>

    @GET("anime/{id}")
    fun getAnimeDetails(@Path("id") id: Int): Call<AnimeDetailResponse>
}
