package com.example.rickmortytask.di

import com.example.rickmortytask.core.network.networkModule
import com.example.rickmortytask.repository.repoModule
import com.example.rickmortytask.data.remote.remoteDataSource

val koinModules = listOf(
    networkModule,
    repoModule,
    remoteDataSource,
    viewModules
)