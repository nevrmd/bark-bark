package com.nevrmd.barkbark.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.nevrmd.barkbark.R
import com.nevrmd.barkbark.data.source.local.BreedRepository
import com.nevrmd.barkbark.data.source.local.database.BreedDatabase
import com.nevrmd.barkbark.databinding.FragmentHomeBinding
import com.nevrmd.barkbark.domain.BreedEntity
import com.nevrmd.barkbark.presentation.BaseFragment
import com.nevrmd.barkbark.presentation.ViewModelFactory
import com.nevrmd.barkbark.presentation.breed.BreedAdapter
import com.nevrmd.barkbark.presentation.breed.OnBreedClickListener

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(
    viewModelClass = HomeViewModel::class.java
) {

    override fun createViewModelFactory(): ViewModelFactory {
        val dao = BreedDatabase.getInstance(requireContext()).breedDao
        val repository = BreedRepository(dao)
        return ViewModelFactory(repository)
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false).apply {
        if(viewModel.getBreeds().value.isNullOrEmpty())
            viewModel.insertAllBreeds()
        initRecyclerView()
    }

    private fun FragmentHomeBinding.initRecyclerView() = with(rvBreeds) {
        layoutManager = GridLayoutManager(requireContext(), GRID_LAYOUT_SPAN_COUNT)
        viewModel.getBreeds().observe(viewLifecycleOwner) { list ->
            adapter = BreedAdapter(list, object : OnBreedClickListener {
                override fun onClick(breed: BreedEntity) {
                    breedClicked()
                }

                override fun onLongClick(breed: BreedEntity) {
                    breedLongClicked()
                }
            })
        }
    }

    private fun breedClicked() {
        TODO("Opening another fragment to view more info")
    }

    private fun breedLongClicked() {
        TODO("Implement reporting info")
    }

//    private fun navigateToNoteFragment() {
//        Navigation.findNavController(binding.root).navigate(R.id.action_homeFragment_to_breedFragment)
//    }

    private companion object {

        const val GRID_LAYOUT_SPAN_COUNT = 2
    }
}