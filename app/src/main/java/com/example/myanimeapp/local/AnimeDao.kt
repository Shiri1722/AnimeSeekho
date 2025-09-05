package com.example.myanimeapp.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myanimeapp.model.AnimeEntity

@Dao
interface AnimeDao {

    @Query("SELECT * FROM anime")
    fun getAllAnime(): LiveData<List<AnimeEntity>>  // LiveData for observing changes

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnime(anime: List<AnimeEntity>)

    @Query("DELETE FROM anime")
    suspend fun clearAll()
}
