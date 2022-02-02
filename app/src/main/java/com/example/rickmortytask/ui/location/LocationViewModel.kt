package com.example.rickmortytask.ui.location

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickmortytask.core.network.result.Resource
import com.example.rickmortytask.models.Location
import com.example.rickmortytask.models.RickAndMorty
import com.example.rickmortytask.repository.Repository

class LocationViewModel(private val repository: Repository) : ViewModel() {

    val loading = MutableLiveData<Boolean>()

    fun getLocation(): LiveData<Resource<RickAndMorty<Location>>> {
        return repository.getLocation()
    }

    fun getLocationByName(name: String): LiveData<Resource<RickAndMorty<Location>>> {
        return repository.getLocationsByNameForLocFrag(name)
    }

}