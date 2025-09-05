package com.example.myanimeapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myanimeapp.R
import com.example.myanimeapp.model.Anime


class AnimeListAdapter(
    private val animeList: List<Anime>,
    private val onItemClick: (Anime) -> Unit  // Click listener lambda
) : RecyclerView.Adapter<AnimeListAdapter.AnimeViewHolder>() {

    inner class AnimeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val posterImageView: ImageView = itemView.findViewById(R.id.animePosterImageView)
        val titleTextView: TextView = itemView.findViewById(R.id.animeTitleTextView)
        val episodesTextView: TextView = itemView.findViewById(R.id.episodesTextView)
        val ratingTextView: TextView = itemView.findViewById(R.id.ratingTextView)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClick(animeList[position])
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_anime, parent, false)
        return AnimeViewHolder(view)
    }

    override fun getItemCount(): Int = animeList.size

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        val anime = animeList[position]
        holder.titleTextView.text = anime.title
        holder.episodesTextView.text = "Episodes: ${anime.episodes ?: "N/A"}"
        holder.ratingTextView.text = "Rating: ${anime.score ?: "N/A"}"
        Glide.with(holder.posterImageView.context)
            .load(anime.images.jpg.imageurl)
            .into(holder.posterImageView)
    }
}
