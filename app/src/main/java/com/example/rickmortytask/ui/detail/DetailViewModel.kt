package com.example.rickmortytask.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickmortytask.core.network.result.Resource
import com.example.rickmortytask.models.Character
import com.example.rickmortytask.models.Episode
import com.example.rickmortytask.models.Location
import com.example.rickmortytask.repository.Repository

class DetailViewModel(private val repository: Repository) : ViewModel() {

    val loading = MutableLiveData<Boolean>()

    fun getLocationById(locationId: Int): LiveData<Resource<Location>> {
        return repository.getLocationById(locationId)
    }

    fun getCharacterById(characterId: Int): LiveData<Resource<Character>> {
        return repository.getCharacterById(characterId)
    }

    fun getEpisodeId(episodeId: Int): LiveData<Resource<Episode>> {
        return repository.getEpisodeById(episodeId)
    }


}