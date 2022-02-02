package com.example.rickmortytask.ui.character

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickmortytask.core.network.result.Resource
import com.example.rickmortytask.models.Character
import com.example.rickmortytask.models.RickAndMorty
import com.example.rickmortytask.repository.Repository

class CharacterViewModel(private val repository: Repository) : ViewModel() {

    val loading = MutableLiveData<Boolean>()

    fun getCharacter(): LiveData<Resource<RickAndMorty<Character>>> {
        return repository.getCharacter()
    }

    fun getCharacterByName(name: String): LiveData<Resource<RickAndMorty<Character>>> {
        return repository.getCharacterByName(name)
    }

    fun getCharacterByStatus(status: String): LiveData<Resource<RickAndMorty<Character>>> {
        return repository.getCharacterByStatus(status)
    }

    fun getCharacterByGender(gender: String): LiveData<Resource<RickAndMorty<Character>>> {
        return repository.getCharacterByGender(gender)
    }

    fun getCharacterByGenderAndStatus(
        status: String,
        gender: String
    ): LiveData<Resource<RickAndMorty<Character>>> {
        return repository.getCharacterByGenderAndStatus(gender, status)
    }


}