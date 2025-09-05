package com.example.myanimeapp.repository


import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myanimeapp.util.NetworkUtils
import com.example.myanimeapp.apiservice.ApiService
import com.example.myanimeapp.local.AppDatabase
import com.example.myanimeapp.model.AnimeEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AnimeRepository(
    private val db: AppDatabase,
    private val apiService: ApiService,
    private val context: Context
) {
    val animeList: LiveData<List<AnimeEntity>> = db.animeDao().getAllAnime()
    private val _networkStatus = MutableLiveData<Boolean>()
    val networkStatus: LiveData<Boolean> = _networkStatus

    fun fetchAnimeAndSync() {
        if (NetworkUtils.isOnline(context)) {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val response = apiService.getTopAnime().execute()
                    if (response.isSuccessful) {
                        val animeEntities = response.body()?.data?.map {
                            AnimeEntity(
                                mal_id = it.malid,
                                title = it.title,
                                episodes = it.episodes,
                                score = it.score,
                                imageUrl = it.images.jpg.imageurl,
                                synopsis = null,
                                genres = null
                            )
                        } ?: emptyList()

                        db.animeDao().clearAll()
                        db.animeDao().insertAnime(animeEntities)
                        _networkStatus.postValue(true)
                    } else {
                        _networkStatus.postValue(false)
                    }
                } catch (e: Exception) {
                    _networkStatus.postValue(false)
                }
            }
        } else {
            _networkStatus.postValue(false)
        }
    }
}
