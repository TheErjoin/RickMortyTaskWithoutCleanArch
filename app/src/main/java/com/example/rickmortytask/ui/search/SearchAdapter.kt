package com.example.rickmortytask.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickmortytask.R
import com.example.rickmortytask.databinding.ItemCharacterBinding
import com.example.rickmortytask.databinding.ItemEpisodeBinding
import com.example.rickmortytask.databinding.ItemLocationBinding
import com.example.rickmortytask.extensions.glide
import com.example.rickmortytask.models.Character
import com.example.rickmortytask.ui.character.CharacterFragment
import com.example.rickmortytask.ui.episode.EpisodeFragment
import com.example.rickmortytask.ui.location.LocationFragment

class SearchAdapter(
    private var list: ArrayList<Character>,
    private var onClick: (type: String, id: Int) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            CHARACTER_L -> {
                return CharacterViewHolder(
                    ItemCharacterBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            EPISODE_L -> {
                return EpisodeViewHolder(
                    ItemEpisodeBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            else -> {
                return LocationViewHolder(
                    ItemLocationBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }
    }


    override fun getItemCount(): Int = list.size


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when {
            getItemViewType(position) == CHARACTER_L -> {
                (holder as CharacterViewHolder).bind(list[position])
            }
            getItemViewType(position) == LOCATION_L -> {
                (holder as LocationViewHolder).onBind(list[position])
            }
            else -> {
                (holder as EpisodeViewHolder).onBind(list[position])
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when {
            list[position].image?.isNotEmpty() == true -> {
                CHARACTER_L
            }
            list[position].dimension?.isNotEmpty() == true -> {
                LOCATION_L
            }
            else -> {
                EPISODE_L
            }
        }
    }

    inner class CharacterViewHolder(private val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(result: Character) {
            result.image?.let { binding.ivCharacter.glide(it) }
            binding.tvCharacterName.text = result.name
            binding.tvLastKnownLocation.text = result.location?.name
            binding.tvStatus.text = result.status
            binding.tvFirstSeenEpisode.text = result.origin?.name

            when (result.status) {
                itemView.context.getString(R.string.alive) -> {
                    binding.ivIndicator.setBackgroundResource(R.drawable.alive_status)
                }
                itemView.context.getString(R.string.dead) -> {
                    binding.ivIndicator.setBackgroundResource(R.drawable.dead_status)
                }
                else -> {
                    binding.ivIndicator.setBackgroundResource(R.drawable.unknown_status)
                }
            }
            itemView.setOnClickListener {
                result.id?.let { it1 -> onClick(CharacterFragment.CHARACTER_ID, it1) }
            }
        }

    }

    inner class EpisodeViewHolder(private val binding: ItemEpisodeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(episode: Character) {
            binding.tvNameEpisode.text = episode.name
            binding.tvCreatedAt.text = episode.air_date

            itemView.setOnClickListener {
                episode.id?.let { it1 -> onClick(EpisodeFragment.EPISODE_ID, it1) }
            }
        }
    }

    inner class LocationViewHolder(private val binding: ItemLocationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(locationRick: Character) {
            binding.tvNameEpisode.text = locationRick.name
            binding.tvCreatedAt.text = locationRick.type

            itemView.setOnClickListener {
                locationRick.id?.let { it1 -> onClick(LocationFragment.LOCATION_ID, it1) }
            }
        }
    }

    companion object {
        const val CHARACTER_L = 1
        const val LOCATION_L = 2
        const val EPISODE_L = 3
    }
}