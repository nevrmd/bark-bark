package com.nevrmd.barkbark.presentation.breed

import androidx.lifecycle.viewModelScope
import com.nevrmd.barkbark.data.source.local.BreedRepository
import com.nevrmd.barkbark.domain.BreedEntity
import com.nevrmd.barkbark.presentation.BaseViewModel
import kotlinx.coroutines.launch

class BreedViewModel(private val repository: BreedRepository): BaseViewModel<BreedState>(
    initialState = BreedState()
) {

    fun onSaveButtonClick(title: String, body: String) = viewModelScope.launch {
    }

    fun delete(breed: BreedEntity) = viewModelScope.launch {
        repository.delete(breed)
    }

    private suspend fun insert(breed: BreedEntity) {
        repository.insert(breed)
    }

    private suspend fun update(breed: BreedEntity) {
        repository.update(breed)
    }
}