package com.example.rickmortytask.ui.character

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickmortytask.R
import com.example.rickmortytask.databinding.ItemCharacterBinding
import com.example.rickmortytask.extensions.glide
import com.example.rickmortytask.models.Character


class CharacterAdapter(
    private var list: ArrayList<Character>,
    private val onClick: (characterId: Int) -> Unit
) :
    RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
            ItemCharacterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

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
                result.id?.let { it1 -> onClick(it1) }
            }
        }
    }
}