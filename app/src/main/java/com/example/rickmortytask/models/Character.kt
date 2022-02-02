package com.example.rickmortytask.models

data class Character(
    var created: String? = null,
    var episodes: List<Any>? = null,
    var gender: String? = null,
    var id: Int? = null,
    var image: String? = null,
    var location: CharacterLocation? = null,
    var name: String? = null,
    var origin: Origin? = null,
    var species: String? = null,
    var status: String? = null,
    var type: String? = null,
    var url: String? = null,
    val air_date: String? = null,
    var residents: List<Any>? = null,
    var characters: List<Any>?,
    var dimension: String? = null
)
