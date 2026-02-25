package com.me.base

sealed class UiState {
    object Idle : UiState()
    object Loading : UiState()
    data class Error(val throwable: Throwable) : UiState()
}
