package com.nevrmd.barkbark.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

abstract class BaseViewModel<S>(
    initialState: S
) : ViewModel() {

    private val statesFlow by lazy { MutableStateFlow(initialState) }

    val state
        get() = statesFlow.value

    protected fun updateState(updater: S.() -> S) {
        statesFlow.update(updater)
    }
}