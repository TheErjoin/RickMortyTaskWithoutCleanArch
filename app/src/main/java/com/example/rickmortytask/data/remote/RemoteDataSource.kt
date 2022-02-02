package com.example.rickmortytask.data.remote

import com.example.rickmortytask.core.ui.BaseDataSource
import org.koin.dsl.module

val remoteDataSource = module {
    factory { RemoteDataSource(get()) }
}

class RemoteDataSource(private val rickAndMortyApi: RickAndMortyApi) : BaseDataSource() {

    suspend fun getCharacter() = getResult {
        rickAndMortyApi.getCharacter()
    }

    suspend fun getEpisode() = getResult {
        rickAndMortyApi.getEpisode()
    }

    suspend fun getLocation() = getResult {
        rickAndMortyApi.getLocation()
    }

    suspend fun getLocationById(locationId: Int) = getResult {
        rickAndMortyApi.getLocationById(locationId)
    }

    suspend fun getCharacterById(characterId: Int) = getResult {
        rickAndMortyApi.getCharacterById(characterId)
    }

    suspend fun getEpisodeById(episodeId: Int) = getResult {
        rickAndMortyApi.getEpisodeById(episodeId)
    }

    suspend fun getCharacterByName(name: String) = getResult {
        rickAndMortyApi.getCharacterByName(name)
    }

    suspend fun getLocationByName(name: String) = getResult {
        rickAndMortyApi.getLocationsByName(name)
    }

    suspend fun getLocationsByNameForLocFrag(name: String) = getResult {
        rickAndMortyApi.getLocationByNameForLocFrag(name)
    }

    suspend fun getEpisodeByName(name: String) = getResult {
        rickAndMortyApi.getEpisodeByName(name)
    }

    suspend fun getEpisodeByNameForEpisodeFrag(name: String) = getResult {
        rickAndMortyApi.getEpisodeByNameForEpisodFrag(name)
    }

    suspend fun getCharacterByStatus(status: String) = getResult {
        rickAndMortyApi.getCharacterByStatus(status)
    }

    suspend fun getCharacterByGender(gender: String) = getResult {
        rickAndMortyApi.getCharacterByGender(gender)
    }

    suspend fun getCharacterByStatusAndGender(status: String, gender: String) = getResult {
        rickAndMortyApi.getCharacterByStatusAndGender(status, gender)
    }

}