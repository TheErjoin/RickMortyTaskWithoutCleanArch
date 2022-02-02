package com.example.rickmortytask.ui.detail

import androidx.core.view.isVisible
import com.example.rickmortytask.R
import com.example.rickmortytask.core.ui.BaseFragment
import com.example.rickmortytask.databinding.FragmentDetailBinding
import com.example.rickmortytask.extensions.glide
import com.example.rickmortytask.ui.character.CharacterFragment
import com.example.rickmortytask.ui.episode.EpisodeFragment
import com.example.rickmortytask.ui.location.LocationFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : BaseFragment<FragmentDetailBinding>() {

    private val viewModel: DetailViewModel by viewModel()
    private var locationId = 0
    private var characterId = 0
    private var episodeId = 0

    override fun bind(): FragmentDetailBinding {
        return FragmentDetailBinding.inflate(layoutInflater)
    }

    override fun setupObservers() {
        when {
            locationId != 0 -> {
                viewModel.getLocationById(locationId).observe(this, {
                    val data = it.data
                    binding.tvName.text = data?.name
                    binding.tvGenderUp.text = getString(R.string.dimension)
                    binding.tvGender.text = data?.dimension
                    binding.tvStatusUp.text = getString(R.string.planet_type)
                    binding.tvStatus.text = data?.type
                    binding.tvTypeUp.text = getString(R.string.data_created)
                    binding.tvType.text = data?.created
                    binding.tvLocation.isVisible = false
                    binding.tvLocationUp.isVisible = false
                })
            }
            characterId != 0 -> {
                viewModel.getCharacterById(characterId).observe(this, {
                    val data = it.data
                    binding.ivAvatar.isVisible = true
                    binding.ivAvatar.glide(data?.image.toString())
                    binding.tvName.text = data?.name
                    binding.tvLocation.text = data?.location?.name
                    binding.tvType.text = data?.species
                    binding.tvGender.text = data?.gender
                    binding.tvStatus.text = data?.status
                })
            }
            episodeId != 0 -> {
                viewModel.getEpisodeId(episodeId).observe(this, {
                    val data = it.data
                    binding.tvName.text = data?.name
                    binding.tvGender.text = data?.air_date
                    binding.tvGenderUp.text = getString(R.string.data_created)
                    binding.tvStatusUp.text = getString(R.string.episode)
                    binding.tvLocation.isVisible = false
                    binding.tvLocationUp.isVisible = false
                    binding.tvStatus.text = data?.episode
                    binding.tvType.isVisible = false
                    binding.tvTypeUp.isVisible = false
                })
            }

        }
    }

    override fun setupUI() {
        locationId = arguments?.getInt(LocationFragment.LOCATION_ID)!!
        characterId = arguments?.getInt(CharacterFragment.CHARACTER_ID)!!
        episodeId = arguments?.getInt(EpisodeFragment.EPISODE_ID)!!
    }

}