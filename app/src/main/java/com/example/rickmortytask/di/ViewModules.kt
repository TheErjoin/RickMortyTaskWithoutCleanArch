package com.example.rickmortytask.di

import com.example.rickmortytask.ui.character.CharacterViewModel
import com.example.rickmortytask.ui.detail.DetailViewModel
import com.example.rickmortytask.ui.episode.EpisodeViewModel
import com.example.rickmortytask.ui.location.LocationViewModel
import com.example.rickmortytask.ui.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val viewModules: Module = module {
    viewModel { CharacterViewModel(get()) }
    viewModel { EpisodeViewModel(get()) }
    viewModel { LocationViewModel(get()) }
    viewModel { DetailViewModel(get()) }
    viewModel { SearchViewModel(get()) }
}