package com.example.myanimeapp.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myanimeapp.model.AnimeEntity

@Database(entities = [AnimeEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun animeDao(): AnimeDao
}
