package com.example.myanimeapp.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myanimeapp.ui.detail.AnimeDetailActivity
import com.example.myanimeapp.R
import com.example.myanimeapp.apiservice.RetrofitClient
import com.example.myanimeapp.local.DatabaseClient
import com.example.myanimeapp.model.Anime
import com.example.myanimeapp.model.ImageUrl
import com.example.myanimeapp.model.Images
import com.example.myanimeapp.repository.AnimeRepository


class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AnimeListAdapter
    private lateinit var repository: AnimeRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = DatabaseClient.getInstance(this)
        repository = AnimeRepository(db, RetrofitClient.instance, this)

        recyclerView = findViewById(R.id.animeRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Observe LiveData for local anime saved in Room
        repository.animeList.observe(this, Observer { entities ->
            val animeList = entities.map {
                Anime(
                    malid = it.mal_id,
                    title = it.title,
                    episodes = it.episodes,
                    score = it.score,
                    images = Images(jpg = ImageUrl(imageurl = it.imageUrl))
                )
            }
            // Initialize or update adapter
            if (::adapter.isInitialized) {
                adapter = AnimeListAdapter(animeList) { anime -> launchDetail(anime) }
                recyclerView.adapter = adapter
            } else {
                adapter = AnimeListAdapter(animeList) { anime -> launchDetail(anime) }
                recyclerView.adapter = adapter
            }
        })

        // Trigger sync with API
        repository.fetchAnimeAndSync()

        // Show online/offline status
        repository.networkStatus.observe(this) { isOnline ->
            Toast.makeText(this, if (isOnline) "Online: Data synced" else "Offline: Showing cached data", Toast.LENGTH_SHORT).show()
        }
    }

    private fun launchDetail(anime: Anime) {
        val intent = Intent(this, AnimeDetailActivity::class.java)
        intent.putExtra("anime_id", anime.malid)
        startActivity(intent)
    }
}
