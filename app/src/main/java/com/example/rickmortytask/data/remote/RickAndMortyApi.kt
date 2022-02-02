package com.example.rickmortytask.data.remote

import com.example.rickmortytask.models.Character
import com.example.rickmortytask.models.Episode
import com.example.rickmortytask.models.Location
import com.example.rickmortytask.models.RickAndMorty
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyApi {

    //Character
    @GET("character")
    suspend fun getCharacter(): Response<RickAndMorty<Character>>

    @GET("character/{id}")
    suspend fun getCharacterById(
        @Path("id") id: Int
    ): Response<Character>

    @GET("character/")
    suspend fun getCharacterByName(
        @Query("name") name: String
    ): Response<RickAndMorty<Character>>

    @GET("character/")
    suspend fun getCharacterByStatus(
        @Query("status") status: String,
    ): Response<RickAndMorty<Character>>

    @GET("character/")
    suspend fun getCharacterByGender(
        @Query("gender") gender: String,
    ): Response<RickAndMorty<Character>>

    @GET("character/")
    suspend fun getCharacterByStatusAndGender(
        @Query("status") status: String,
        @Query("gender") gender: String,
    ): Response<RickAndMorty<Character>>

    //Location
    @GET("location")
    suspend fun getLocation(): Response<RickAndMorty<Location>>

    @GET("location/{id}")
    suspend fun getLocationById(
        @Path("id") page: Int
    ): Response<Location>

    @GET("location/")
    suspend fun getLocationByNameForLocFrag(
        @Query("name") name: String
    ): Response<RickAndMorty<Location>>

    @GET("location/")
    suspend fun getLocationsByName(
        @Query("name") name: String
    ): Response<RickAndMorty<Character>>

    // Episode
    @GET("episode")
    suspend fun getEpisode(): Response<RickAndMorty<Episode>>

    @GET("episode/{id}")
    suspend fun getEpisodeById(
        @Path("id") id: Int
    ): Response<Episode>

    @GET("episode/")
    suspend fun getEpisodeByName(
        @Query("name") name: String
    ): Response<RickAndMorty<Character>>

    @GET("episode/")
    suspend fun getEpisodeByNameForEpisodFrag(
        @Query("name") name: String
    ): Response<RickAndMorty<Episode>>

}