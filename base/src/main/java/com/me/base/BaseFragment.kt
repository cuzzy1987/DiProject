package com.me.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.launch
import androidx.lifecycle.repeatOnLifecycle

abstract class BaseFragment<VB : ViewBinding, VM : BaseViewModel>
    : Fragment() {

    private var _binding: VB? = null
    protected val binding get() = _binding!!

    protected abstract val viewModel: VM
    abstract fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): VB

    abstract fun initView()
    abstract fun initObserver()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = createBinding(inflater, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView()
        initObserver()
        observeBase()
    }

    private fun observeBase() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    when (it) {
                        is UiState.Loading -> showLoading()
                        is UiState.Error -> showError(it.throwable)
                        else -> hideLoading()
                    }
                }
            }
        }
    }

    protected open fun showLoading() {}
    protected open fun hideLoading() {}
    protected open fun showError(t: Throwable) {}

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
