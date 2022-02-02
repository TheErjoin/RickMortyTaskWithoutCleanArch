package com.example.rickmortytask.ui.episode

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickmortytask.databinding.ItemEpisodeBinding
import com.example.rickmortytask.models.Episode

class EpisodeAdapter(
    private var list: ArrayList<Episode>,
    private val onClick: (episodeId: Int) -> Unit
) :
    RecyclerView.Adapter<EpisodeAdapter.EpisodeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        return EpisodeViewHolder(
            ItemEpisodeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size

    inner class EpisodeViewHolder(private val binding: ItemEpisodeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(episode: Episode) {
            binding.tvNameEpisode.text = episode.name
            binding.tvCreatedAt.text = episode.air_date

            itemView.setOnClickListener {
                episode.id?.let { it1 -> onClick(it1) }
            }
        }
    }

}