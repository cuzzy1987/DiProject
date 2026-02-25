package com.me.base

sealed class UiEvent {
    data class Toast(val msg: String) : UiEvent()
    object Finish : UiEvent()
}
