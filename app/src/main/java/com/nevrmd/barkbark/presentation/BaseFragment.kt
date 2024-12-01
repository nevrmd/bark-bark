package com.nevrmd.barkbark.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB: ViewBinding, VM: ViewModel>(
    private val viewModelClass: Class<VM>,
) : Fragment() {

    protected val viewModel: VM by lazy(LazyThreadSafetyMode.NONE) {
        ViewModelProvider(this, createViewModelFactory())[viewModelClass]
    }

    private lateinit var _binding: VB
    protected val binding
        get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = getViewBinding(inflater, container).also { viewBinding ->
        _binding = viewBinding
    }.root

    protected abstract fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): VB

    protected abstract fun createViewModelFactory(): ViewModelProvider.Factory
}