package com.example.myanimeapp.local

import android.content.Context
import androidx.room.Room

object DatabaseClient {
    private var INSTANCE: AppDatabase? = null

    fun getInstance(context: Context): AppDatabase {
        if (INSTANCE == null) {
            synchronized(AppDatabase::class) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "anime_database"
                ).build()
            }
        }
        return INSTANCE!!
    }
}
