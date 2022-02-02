package com.example.rickmortytask.ui.episode

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickmortytask.core.network.result.Resource
import com.example.rickmortytask.models.Episode
import com.example.rickmortytask.models.RickAndMorty
import com.example.rickmortytask.repository.Repository

class EpisodeViewModel(private val repository: Repository) : ViewModel() {

    val loading = MutableLiveData<Boolean>()

    fun getEpisode(): LiveData<Resource<RickAndMorty<Episode>>> {
        return repository.getEpisode()
    }

    fun getEpisodeByNameForEpFrag(name: String): LiveData<Resource<RickAndMorty<Episode>>> {
        return repository.getEpisodeByNameForEpisodeFrag(name)
    }

}