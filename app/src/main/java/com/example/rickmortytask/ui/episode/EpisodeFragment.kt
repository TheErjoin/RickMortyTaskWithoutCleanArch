package com.example.rickmortytask.ui.episode

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickmortytask.R
import com.example.rickmortytask.core.network.result.Status
import com.example.rickmortytask.core.ui.BaseFragment
import com.example.rickmortytask.databinding.FragmentEpisodeBinding
import com.example.rickmortytask.models.Episode
import org.koin.androidx.viewmodel.ext.android.viewModel

class EpisodeFragment : BaseFragment<FragmentEpisodeBinding>() {

    private val viewModel: EpisodeViewModel by viewModel()
    private val adapterE: EpisodeAdapter by lazy {
        EpisodeAdapter(
            list,
            this::onClick
        )
    }

    private fun onClick(episodeId: Int) {
        Bundle().apply {
            putSerializable(EPISODE_ID, episodeId)
            findNavController().navigate(R.id.detailFragment, this)
        }
    }

    private var list: ArrayList<Episode> = arrayListOf()

    override fun bind(): FragmentEpisodeBinding {
        return FragmentEpisodeBinding.inflate(layoutInflater)
    }

    override fun setupObservers() {
        viewModel.loading.observe(this, {
            binding.progress.isVisible = it
        })
        viewModel.getEpisode().observe(this, {
            when (it.status) {
                Status.LOADING -> viewModel.loading.postValue(true)
                Status.SUCCESS -> {
                    initAdapter(it.data?.results as ArrayList<Episode>)
                    viewModel.loading.postValue(false)
                }
                Status.ERROR -> viewModel.loading.postValue(false)
            }
        })
        binding.etEpisode.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            @SuppressLint("NotifyDataSetChanged")
            override fun afterTextChanged(p0: Editable?) {
                viewModel.getEpisodeByNameForEpFrag(p0.toString()).observe(viewLifecycleOwner, {
                    when (it.status) {
                        Status.LOADING -> viewModel.loading.postValue(true)
                        Status.SUCCESS -> {
                            binding.rvEpisode.isVisible = true
                            viewModel.loading.postValue(true)
                            if (it.data?.results != null) {
                                viewModel.loading.postValue(false)
                                list.clear()
                                list.addAll(it.data.results!!)
                                adapterE.notifyDataSetChanged()
                            }
                        }
                        Status.ERROR -> {
                            viewModel.loading.postValue(false)
                            Toast.makeText(
                                context,
                                getString(R.string.error),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                })
            }
        })
    }

    private fun initAdapter(list: ArrayList<Episode>) {
        this.list = list
        binding.rvEpisode.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adapterE
        }
    }

    override fun setupUI() {

    }

    companion object {
        const val EPISODE_ID = "key_ep"
    }
}