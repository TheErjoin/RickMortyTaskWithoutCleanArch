package com.example.rickmortytask.models

import com.google.gson.annotations.SerializedName

data class Episode(
    var air_date: String?,
    var characters: List<Any>?,
    var created: String?,
    var episode: String?,
    var id: Int?,
    var name: String?,
    var url: String?
)