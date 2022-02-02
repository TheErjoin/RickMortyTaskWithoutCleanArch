package com.example.rickmortytask.ui.search

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickmortytask.R
import com.example.rickmortytask.core.network.result.Status
import com.example.rickmortytask.core.ui.BaseFragment
import com.example.rickmortytask.databinding.FragmentSearchBinding
import com.example.rickmortytask.models.Character
import com.example.rickmortytask.ui.character.CharacterFragment
import com.example.rickmortytask.ui.episode.EpisodeFragment
import com.example.rickmortytask.ui.location.LocationFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : BaseFragment<FragmentSearchBinding>() {

    private val viewModel: SearchViewModel by viewModel()
    private var list = arrayListOf<Character>()
    private val adapterS: SearchAdapter by lazy {
        SearchAdapter(list, this::onClick)
    }

    private fun onClick(type: String, id: Int) {
        Bundle().apply {
            val layout = when (type) {
                CharacterFragment.CHARACTER_ID -> {
                    CharacterFragment.CHARACTER_ID
                }
                LocationFragment.LOCATION_ID -> {
                    LocationFragment.LOCATION_ID
                }
                else -> {
                    EpisodeFragment.EPISODE_ID
                }
            }
            putSerializable(layout, id)
            findNavController().navigate(R.id.detailFragment, this)
        }
    }


    override fun setupObservers() {
        viewModel.loading.observe(this, {
            binding.progress.isVisible = it
        })
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getAllData(name: String) {
        viewModel.getCharacterByName(name).observe(this, {
            when (it.status) {
                Status.LOADING -> viewModel.loading.postValue(true)
                Status.SUCCESS -> {
                    viewModel.loading.postValue(true)
                    if (it.data?.results != null) {
                        viewModel.loading.postValue(false)
                        list.addAll(it.data.results!!)
                        adapterS.notifyDataSetChanged()
                    }
                }
                Status.ERROR -> {
                    viewModel.loading.postValue(false)
                }
            }
        })
        viewModel.getLocationsByName(name).observe(this, {
            when (it.status) {
                Status.SUCCESS -> {
                    viewModel.loading.postValue(true)
                    if (it.data?.results != null) {
                        viewModel.loading.postValue(false)
                        list.addAll(it.data.results!!)
                        adapterS.notifyDataSetChanged()
                    }
                }
                Status.LOADING -> viewModel.loading.postValue(true)
                Status.ERROR -> {
                    viewModel.loading.postValue(false)
                }
            }
        })
        viewModel.getEpisodeByName(name).observe(this, {
            when (it.status) {
                Status.SUCCESS -> {
                    viewModel.loading.postValue(true)
                    if (it.data?.results != null) {
                        viewModel.loading.postValue(false)
                        list.addAll(it.data.results!!)
                        adapterS.notifyDataSetChanged()
                    }
                }
                Status.LOADING -> viewModel.loading.postValue(false)
                Status.ERROR -> {
                    viewModel.loading.postValue(false)
                }
            }
        })

    }

    override fun setupUI() {
        binding.rvSearch.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adapterS
        }
        binding.btnSearch.setOnClickListener {
            if (binding.etSearch.text.toString().trim().isNotEmpty()) {
                list.clear()
                getAllData(binding.etSearch.text.trim().toString())
            }
        }
    }

    override fun bind(): FragmentSearchBinding {
        return FragmentSearchBinding.inflate(layoutInflater)
    }
}