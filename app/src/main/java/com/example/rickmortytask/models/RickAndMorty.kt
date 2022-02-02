package com.example.rickmortytask.models

data class RickAndMorty<T>(
    var info: Info?,
    var results: ArrayList<T>?
)