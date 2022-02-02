package com.example.rickmortytask.ui.location

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickmortytask.databinding.ItemLocationBinding
import com.example.rickmortytask.models.Location

class LocationAdapter(
    private var list: ArrayList<Location>,
    private val onClick: (locationId: Int) -> Unit
) : RecyclerView.Adapter<LocationAdapter.LocationViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        return LocationViewHolder(
            ItemLocationBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size

    inner class LocationViewHolder(private val binding: ItemLocationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(locationRick: Location) {
            binding.tvNameEpisode.text = locationRick.name
            binding.tvCreatedAt.text = locationRick.type

            itemView.setOnClickListener {
                locationRick.id?.let { it1 -> onClick(it1) }
            }
        }
    }
}