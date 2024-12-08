package com.nevrmd.barkbark.presentation.breed

import com.nevrmd.barkbark.data.source.local.BreedRepository
import com.nevrmd.barkbark.domain.BreedEntity
import com.nevrmd.barkbark.presentation.BaseViewModel

class BreedViewModel(private val repository: BreedRepository): BaseViewModel<BreedState>(
    initialState = BreedState()
) {

    fun saveArgs(args: BreedFragmentArgs) {
        updateState {
            state.copy(
                id = args.id,
            )
        }
    }

    fun getBreedById(id: Int): BreedEntity {
        return repository.getBreedById(id)
    }
}