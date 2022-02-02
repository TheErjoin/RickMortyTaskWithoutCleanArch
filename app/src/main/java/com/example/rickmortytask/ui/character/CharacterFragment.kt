package com.example.rickmortytask.ui.character

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
import com.example.rickmortytask.databinding.FragmentCharacterBinding
import com.example.rickmortytask.models.Character
import com.example.rickmortytask.ui.customDialog.CustomAlertDialog
import com.example.rickmortytask.ui.customDialog.CustomListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterFragment : BaseFragment<FragmentCharacterBinding>() {

    private val viewModel: CharacterViewModel by viewModel()
    private val adapterC: CharacterAdapter by lazy {
        CharacterAdapter(
            list,
            this::onClick
        )
    }
    private val dialog: CustomAlertDialog by lazy {
        CustomAlertDialog()
    }
    private val unknown = "Unknown"
    private val alive = "Alive"
    private val dead = "Dead"
    private val male = "Male"
    private val female = "Female"

    private fun onClick(characterId: Int) {
        Bundle().apply {
            putSerializable(CHARACTER_ID, characterId)
            findNavController().navigate(R.id.detailFragment, this)
        }
    }

    private var list: ArrayList<Character> = arrayListOf()

    @SuppressLint("NotifyDataSetChanged")
    override fun setupObservers() {
        viewModel.loading.observe(this) {
            binding.progress.isVisible = it
        }
        viewModel.getCharacter().observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    viewModel.loading.postValue(false)
                    if (it.data?.results != null) {
                        initAdapter(it.data.results as ArrayList<Character>)
                        adapterC.notifyDataSetChanged()
                    }
                }
                Status.LOADING -> viewModel.loading.postValue(true)
                Status.ERROR -> viewModel.loading.postValue(false)
            }
        })
        binding.etCharacterName.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            @SuppressLint("NotifyDataSetChanged")
            override fun afterTextChanged(text: Editable?) {
                viewModel.getCharacterByName(text.toString())
                    .observe(viewLifecycleOwner, {
                        when (it.status) {
                            Status.LOADING -> viewModel.loading.postValue(true)
                            Status.SUCCESS -> {
                                binding.rvCharacter.isVisible = true
                                viewModel.loading.postValue(true)
                                if (it.data?.results != null) {
                                    viewModel.loading.postValue(false)
                                    list.clear()
                                    list.addAll(it.data.results!!)
                                    adapterC.notifyDataSetChanged()
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

    override fun setupUI() {
        binding.ivFilter.setOnClickListener {
            dialog.show(parentFragmentManager, "customDialog")
            filterR()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun filterR() {
        viewModel.loading.observe(this) {
            binding.progress.isVisible = it
        }

        dialog.setListener(object : CustomListener {
            override fun status(status: String?) {
                val statusR = when (status) {
                    getString(R.string.alive) -> alive

                    getString(R.string.dead) -> dead

                    else -> unknown

                }
                viewModel.getCharacterByStatus(statusR).observe(viewLifecycleOwner, {
                    when (it.status) {
                        Status.SUCCESS -> {
                            viewModel.loading.postValue(true)
                            if (it.data?.results != null) {
                                viewModel.loading.postValue(false)
                                list.clear()
                                list.addAll(it.data.results!!)
                                adapterC.notifyDataSetChanged()
                            }
                        }
                        Status.LOADING -> viewModel.loading.postValue(true)

                        Status.ERROR -> {
                            viewModel.loading.postValue(false)
                        }
                    }
                })
            }

            override fun gender(gender: String?) {
                val allGender = when (gender) {
                    getString(R.string.male) -> male

                    getString(R.string.female) -> female

                    else -> unknown
                }
                viewModel.getCharacterByGender(allGender).observe(viewLifecycleOwner, {
                    when (it.status) {
                        Status.SUCCESS -> {
                            viewModel.loading.postValue(true)
                            if (it.data?.results != null) {
                                viewModel.loading.postValue(false)
                                list.clear()
                                list.addAll(it.data.results!!)
                                adapterC.notifyDataSetChanged()
                            }
                        }
                        Status.LOADING -> viewModel.loading.postValue(true)

                        Status.ERROR -> {
                            viewModel.loading.postValue(false)
                        }
                    }
                })
            }

            override fun statusAndGender(status: String?, gender: String?) {
                val allGender = when (gender) {
                    getString(R.string.male) -> male

                    getString(R.string.female) -> female

                    else -> unknown
                }
                val statusR = when (status) {
                    getString(R.string.alive) -> alive

                    getString(R.string.dead) -> dead

                    else -> unknown

                }
                viewModel.getCharacterByGenderAndStatus(statusR, allGender)
                    .observe(viewLifecycleOwner, {
                        when (it.status) {
                            Status.SUCCESS -> {
                                viewModel.loading.postValue(true)
                                if (it.data?.results != null) {
                                    viewModel.loading.postValue(false)
                                    list.clear()
                                    list.addAll(it.data.results!!)
                                    adapterC.notifyDataSetChanged()
                                }
                            }
                            Status.LOADING -> viewModel.loading.postValue(true)

                            Status.ERROR -> {
                                viewModel.loading.postValue(false)
                            }
                        }
                    })
            }

        })
    }

    private fun initAdapter(list: ArrayList<Character>) {
        this.list = list
        binding.rvCharacter.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adapterC
        }
    }

    override fun bind(): FragmentCharacterBinding {
        return FragmentCharacterBinding.inflate(layoutInflater)
    }

    companion object {
        const val CHARACTER_ID = "key_cha"
    }
}