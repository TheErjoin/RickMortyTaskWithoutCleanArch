package com.example.rickmortytask.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickmortytask.core.network.result.Resource
import com.example.rickmortytask.models.Character
import com.example.rickmortytask.models.RickAndMorty
import com.example.rickmortytask.repository.Repository

class SearchViewModel(private val repository: Repository) : ViewModel() {

    val loading = MutableLiveData<Boolean>()

    fun getCharacterByName(name: String): LiveData<Resource<RickAndMorty<Character>>> {
        return repository.getCharacterByName(name)
    }

    fun getLocationsByName(name: String): LiveData<Resource<RickAndMorty<Character>>> {
        return repository.getLocationByName(name)
    }

    fun getEpisodeByName(name: String): LiveData<Resource<RickAndMorty<Character>>> {
        return repository.getEpisodeByName(name)
    }

}