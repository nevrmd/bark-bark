package com.nevrmd.barkbark.presentation.breed

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.nevrmd.barkbark.data.source.local.BreedRepository
import com.nevrmd.barkbark.data.source.local.database.BreedDatabase
import com.nevrmd.barkbark.databinding.FragmentBreedBinding
import com.nevrmd.barkbark.presentation.BaseFragment
import com.nevrmd.barkbark.presentation.ViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BreedFragment : BaseFragment<FragmentBreedBinding, BreedViewModel>(
    viewModelClass = BreedViewModel::class.java,
) {

    private val args: BreedFragmentArgs by navArgs()

    override fun createViewModelFactory(): ViewModelProvider.Factory {
        val dao = BreedDatabase.getInstance(requireContext()).breedDao
        val repository = BreedRepository(dao)
        return ViewModelFactory(repository)
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return super.onCreateView(inflater, container, savedInstanceState).also {
            viewModel.saveArgs(args)
            lifecycleScope.launch {
                val breed = withContext(Dispatchers.IO) {
                    viewModel.getBreedById(args.id)
                }
                withContext(Dispatchers.Main) {
                    with(binding) {
                        tvBreedName.text = breed.name
                        tvBreedDescription.text = breed.description
                        tvBreedIsHypoallergenic.text = when (breed.isHypoallergenic) {
                            true -> "Hypoallergenic."
                            false -> "Not hypoallergenic."
                        }
                        tvBreedLifeMinMax.text =
                            "Average life expectancy: ${breed.lifeMin} - ${breed.lifeMax} years"
                    }
                }
            }
        }
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentBreedBinding = FragmentBreedBinding.inflate(inflater, container, false)
}