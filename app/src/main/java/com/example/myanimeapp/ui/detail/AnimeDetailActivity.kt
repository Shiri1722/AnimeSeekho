package com.example.myanimeapp.ui.detail

import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.bumptech.glide.Glide
import com.example.myanimeapp.R
import com.example.myanimeapp.apiservice.RetrofitClient
import com.example.myanimeapp.model.AnimeDetail
import com.example.myanimeapp.model.AnimeDetailResponse


class AnimeDetailActivity : AppCompatActivity() {

    private lateinit var posterImageView: ImageView
    private lateinit var titleTextView: TextView
    private lateinit var synopsisTextView: TextView
    private lateinit var genresTextView: TextView
    private lateinit var castTextView: TextView
    private lateinit var episodesTextView: TextView
    private lateinit var ratingTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anime_detail)

        posterImageView = findViewById(R.id.posterImageView)
        titleTextView = findViewById(R.id.titleTextView)
        synopsisTextView = findViewById(R.id.synopsisTextView)
        genresTextView = findViewById(R.id.genresTextView)
        castTextView = findViewById(R.id.castTextView)
        episodesTextView = findViewById(R.id.episodesTextView)
        ratingTextView = findViewById(R.id.ratingTextView)

        val animeId = intent.getIntExtra("anime_id", -1)
        if (animeId != -1) {
            fetchAnimeDetails(animeId)
        } else {
            Toast.makeText(this, "Invalid Anime ID", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun fetchAnimeDetails(animeId: Int) {
        RetrofitClient.instance.getAnimeDetails(animeId).enqueue(object : Callback<AnimeDetailResponse> {
            override fun onResponse(
                call: Call<AnimeDetailResponse>,
                response: Response<AnimeDetailResponse>
            ) {
                if (response.isSuccessful) {
                    val anime = response.body()?.data
                    if (anime != null) {
                        displayAnimeDetails(anime)
                    }
                } else {
                    Toast.makeText(this@AnimeDetailActivity, "Failed to load details", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<AnimeDetailResponse>, t: Throwable) {
                Toast.makeText(this@AnimeDetailActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun displayAnimeDetails(anime: AnimeDetail) {
        titleTextView.text = anime.title
        synopsisTextView.text = anime.synopsis ?: "No synopsis available"

        genresTextView.text = "Genres: " + anime.genres.joinToString(", ") { it.name }
        castTextView.text = "Producers: " + anime.producers.joinToString(", ") { it.name }
        episodesTextView.text = "Episodes: ${anime.episodes ?: "N/A"}"
        ratingTextView.text = "Score: ${anime.score ?: "N/A"}"

        // Show poster image and open trailer URL externally on click if present
        posterImageView.visibility = ImageView.VISIBLE
        Glide.with(this)
            .load(anime.images.jpg.imageurl)
            .into(posterImageView)

        anime.trailer?.url?.let { trailerUrl ->
            posterImageView.setOnClickListener {
                val intent = android.content.Intent(android.content.Intent.ACTION_VIEW)
                intent.data = Uri.parse(trailerUrl)
                startActivity(intent)
            }
        }
    }
}
