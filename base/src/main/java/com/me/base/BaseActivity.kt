package com.me.base

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB : ViewBinding, VM : BaseViewModel>
    : AppCompatActivity() {

    protected lateinit var binding: VB
    protected abstract val viewModel: VM

    abstract fun createBinding(inflater: LayoutInflater): VB
    abstract fun initView()
    abstract fun initObserver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = createBinding(layoutInflater)
        setContentView(binding.root)

        initView()
        initObserver()
        observeBase()
    }

    private fun observeBase() {
        lifecycleScope.launchWhenStarted {
            viewModel.uiState.collect {
                when (it) {
                    is UiState.Loading -> showLoading()
                    is UiState.Error -> showError(it.throwable)
                    else -> hideLoading()
                }
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.uiEvent.collect {
                handleEvent(it)
            }
        }
    }

    protected open fun showLoading() {}
    protected open fun hideLoading() {}
    protected open fun showError(t: Throwable) {}

    protected open fun handleEvent(event: UiEvent) {
        when (event) {
            is UiEvent.Toast ->
                Toast.makeText(this, event.msg, Toast.LENGTH_SHORT).show()
            UiEvent.Finish -> finish()
        }
    }
}
