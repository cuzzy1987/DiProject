package com.me.diproject.vm

import androidx.lifecycle.viewModelScope
import com.me.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn

class FormViewModel:BaseViewModel() {

    private val _account =  MutableStateFlow("")
    val account: StateFlow<String> = _account

    private val _password =  MutableStateFlow("")
    val password: StateFlow<String> = _password

    val isValid: StateFlow<Boolean> =
        combine(_account, _password) { username, password ->
            username.length >= 3 && password.length >= 6
        }
            .distinctUntilChanged()
            .flowOn(Dispatchers.Default)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = false
            )

    fun onUsernameChanged(text: String) {
        _account.value = text
    }

    fun onPasswordChanged(text: String) {
        _password.value = text
    }
}