package com.example.myanimeapp.model

data class AnimeDetailResponse(
    val data: AnimeDetail
)

data class AnimeDetail(
    val mal_id: Int,
    val title: String,
    val synopsis: String?,
    val genres: List<Genre>,
    val producers: List<Producer>,
    val trailer: Trailer?,
    val episodes: Int?,
    val score: Double?,
    val images: Images
)

data class Genre(
    val mal_id: Int,
    val name: String
)

data class Producer(
    val mal_id: Int,
    val name: String
)

data class Trailer(
    val youtube_id: String?,
    val url: String?,
    val embed_url: String?
)

// Reuse existing Images, ImageUrl classes
