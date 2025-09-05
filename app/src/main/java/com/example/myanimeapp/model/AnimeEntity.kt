package com.example.myanimeapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "anime")
data class AnimeEntity(
    @PrimaryKey val mal_id: Int,
    val title: String,
    val episodes: Int?,
    val score: Double?,
    @ColumnInfo(name = "image_url") val imageUrl: String,
    val synopsis: String?,
    val genres: String?  // store as comma separated string
)
