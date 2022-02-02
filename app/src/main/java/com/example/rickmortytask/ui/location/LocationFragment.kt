package com.example.rickmortytask.ui.location

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
import com.example.rickmortytask.databinding.FragmentLocationBinding
import com.example.rickmortytask.models.Location
import org.koin.androidx.viewmodel.ext.android.viewModel

class LocationFragment : BaseFragment<FragmentLocationBinding>() {

    private val viewModel: LocationViewModel by viewModel()
    private val adapterL: LocationAdapter by lazy {
        LocationAdapter(
            list,
            this::onClick
        )
    }

    private var list = arrayListOf<Location>()

    override fun bind(): FragmentLocationBinding {
        return FragmentLocationBinding.inflate(layoutInflater)
    }

    override fun setupObservers() {
        viewModel.loading.observe(this, {
            binding.progress.isVisible = it
        })
        viewModel.getLocation().observe(this, {
            when (it.status) {
                Status.SUCCESS -> {
                    initAdapter(it.data?.results as ArrayList<Location>)
                    viewModel.loading.postValue(false)
                }
                Status.LOADING -> viewModel.loading.postValue(true)
                Status.ERROR -> {
                    viewModel.loading.postValue(false)
                    Toast.makeText(requireContext(), getString(R.string.error), Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })
        binding.etLocation.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun afterTextChanged(text: Editable?) {
                viewModel.getLocationByName(text.toString())
                    .observe(viewLifecycleOwner, {
                        when (it.status) {
                            Status.LOADING -> viewModel.loading.postValue(true)
                            Status.SUCCESS -> {
                                binding.rvLocation.isVisible = true
                                viewModel.loading.postValue(true)
                                if (it.data?.results != null) {
                                    viewModel.loading.postValue(false)
                                    list.clear()
                                    list.addAll(it.data.results!!)
                                    adapterL.notifyDataSetChanged()
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

    private fun initAdapter(list: ArrayList<Location>) {
        this.list = list
        binding.rvLocation.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adapterL
        }
    }

    override fun setupUI() {

    }

    private fun onClick(locationId: Int) {
        Bundle().apply {
            putSerializable(LOCATION_ID, locationId)
            findNavController().navigate(R.id.detailFragment, this)
        }
    }

    companion object {
        const val LOCATION_ID = "key_loc"
    }
}