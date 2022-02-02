package com.example.rickmortytask.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.rickmortytask.core.network.result.Resource
import com.example.rickmortytask.data.remote.RemoteDataSource
import com.example.rickmortytask.models.Character
import com.example.rickmortytask.models.Episode
import com.example.rickmortytask.models.Location
import com.example.rickmortytask.models.RickAndMorty
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val repoModule = module {
    single { Repository(get()) }
}

class Repository(private val dataSource: RemoteDataSource) {

    //Character
    fun getCharacter(): LiveData<Resource<RickAndMorty<Character>>> = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        val response = dataSource.getCharacter()
        emit(response)
    }

    fun getCharacterById(characterId: Int): LiveData<Resource<Character>> =
        liveData(Dispatchers.IO) {
            emit(Resource.loading(null))
            val response = dataSource.getCharacterById(characterId)
            emit(response)
        }

    fun getCharacterByName(name: String): LiveData<Resource<RickAndMorty<Character>>> =
        liveData(Dispatchers.IO) {
            emit(Resource.loading(null))
            val response = dataSource.getCharacterByName(name)
            emit(response)
        }

    //Episode
    fun getEpisode(): LiveData<Resource<RickAndMorty<Episode>>> = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        val response = dataSource.getEpisode()
        emit(response)
    }

    fun getEpisodeById(episodeId: Int): LiveData<Resource<Episode>> =
        liveData(Dispatchers.IO) {
            emit(Resource.loading(null))
            val response = dataSource.getEpisodeById(episodeId)
            emit(response)
        }

    fun getEpisodeByName(name: String): LiveData<Resource<RickAndMorty<Character>>> =
        liveData(Dispatchers.IO) {
            emit(Resource.loading(null))
            val response = dataSource.getEpisodeByName(name)
            emit(response)
        }

    //Location
    fun getLocation(): LiveData<Resource<RickAndMorty<Location>>> = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        val response = dataSource.getLocation()
        emit(response)
    }

    fun getLocationById(locationId: Int): LiveData<Resource<Location>> =
        liveData(Dispatchers.IO) {
            emit(Resource.loading(null))
            val response = dataSource.getLocationById(locationId)
            emit(response)
        }

    fun getLocationByName(name: String): LiveData<Resource<RickAndMorty<Character>>> =
        liveData(Dispatchers.IO) {
            emit(Resource.loading(null))
            val response = dataSource.getLocationByName(name)
            emit(response)
        }

    fun getLocationsByNameForLocFrag(name: String): LiveData<Resource<RickAndMorty<Location>>> =
        liveData(Dispatchers.IO) {
            emit(Resource.loading(null))
            val response = dataSource.getLocationsByNameForLocFrag(name)
            emit(response)
        }

    fun getEpisodeByNameForEpisodeFrag(name: String): LiveData<Resource<RickAndMorty<Episode>>> =
        liveData(Dispatchers.IO) {
            emit(Resource.loading(null))
            val response = dataSource.getEpisodeByNameForEpisodeFrag(name)
            emit(response)
        }

    fun getCharacterByStatus(status: String): LiveData<Resource<RickAndMorty<Character>>> =
        liveData(Dispatchers.IO) {
            emit(Resource.loading(null))
            val response = dataSource.getCharacterByStatus(status)
            emit(response)
        }

    fun getCharacterByGender(gender: String): LiveData<Resource<RickAndMorty<Character>>> =
        liveData(Dispatchers.IO) {
            emit(Resource.loading(null))
            val response = dataSource.getCharacterByGender(gender)
            emit(response)
        }

    fun getCharacterByGenderAndStatus(
        gender: String,
        status: String
    ): LiveData<Resource<RickAndMorty<Character>>> =
        liveData(Dispatchers.IO) {
            emit(Resource.loading(null))
            val response = dataSource.getCharacterByStatusAndGender(status, gender)
            emit(response)
        }

}