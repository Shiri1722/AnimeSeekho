package com.example.myanimeapp.model

import com.google.gson.annotations.SerializedName
data class AnimeResponse(
    val data: List<Anime>
)

data class Anime(
    @SerializedName("mal_id")
    val malid: Int,
    val title: String,
    val episodes: Int?,
    val score: Double?,
    val images: Images
)

data class Images(
    val jpg: ImageUrl
)

data class ImageUrl(
    @SerializedName("image_url")
    val imageurl: String
)
