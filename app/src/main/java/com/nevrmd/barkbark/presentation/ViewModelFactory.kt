package com.nevrmd.barkbark.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.nevrmd.barkbark.data.source.local.BreedRepository
import com.nevrmd.barkbark.presentation.breed.BreedViewModel
import com.nevrmd.barkbark.presentation.home.HomeViewModel

class ViewModelFactory(private val repository: BreedRepository) : ViewModelProvider.Factory {
    @Suppress("Unchecked_Cast")
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        return when {
            modelClass.isAssignableFrom(BreedViewModel::class.java) -> BreedViewModel(repository) as T
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(repository) as T
            else -> throw IllegalArgumentException("Unknown ViewModel Class")
        }
    }
}