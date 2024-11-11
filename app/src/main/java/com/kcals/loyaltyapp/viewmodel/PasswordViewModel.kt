package com.kcals.loyaltyapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PasswordViewModel @Inject constructor() : ViewModel() {
    private val _currentPassState = MutableStateFlow<PasswordState>(PasswordState.Idle)
    val currentPassState: StateFlow<PasswordState> get() = _currentPassState

    private val _newPassState = MutableStateFlow<PasswordState>(PasswordState.Idle)
    val newPassState: StateFlow<PasswordState> get() = _newPassState

    private val _reTypePassState = MutableStateFlow<PasswordState>(PasswordState.Idle)
    val reTypePassState: StateFlow<PasswordState> get() = _reTypePassState

    val isAllSuccess = MutableStateFlow<Boolean>(false)

    fun verifyCurrentPass(pass: String) {
        viewModelScope.launch {
            _currentPassState.value = when {
                pass.isEmpty() -> PasswordState.Error("Empty")
                pass == "mock123" -> PasswordState.Success
                else -> PasswordState.Error("Incorrect")
            }
        }
    }

    fun verifyNewPass(newPass: String) {
        viewModelScope.launch {
            _newPassState.value = when {
                newPass.isEmpty() -> PasswordState.Error("Empty")
                newPass.length >= 8 -> PasswordState.Success
                else -> PasswordState.Error("Password should be longer than 8 characters")
            }
        }
    }

    fun verifyRetypePass(newPass: String, retype: String) {
        viewModelScope.launch {
            _reTypePassState.value = when {
                retype.isEmpty() -> PasswordState.Error("Empty")
                retype == newPass -> PasswordState.Success
                else -> PasswordState.Error("Mismatch")
            }
        }
    }

    fun fieldFilledUp(passInput: PasswordInput) {
        when (passInput) {
            PasswordInput.CURRENT -> _currentPassState.value = PasswordState.Idle
            PasswordInput.NEW -> _newPassState.value = PasswordState.Idle
            PasswordInput.RETYPE -> _reTypePassState.value = PasswordState.Idle
        }
        isAllSuccess()
    }

    private fun isAllSuccess() {
        viewModelScope.launch {
            if (_currentPassState.value == PasswordState.Success &&
                _newPassState.value == PasswordState.Success &&
                _reTypePassState.value == PasswordState.Success
            ) {
                isAllSuccess.value = true
            }
        }
    }

}

sealed class PasswordState {
    data object Success : PasswordState()
    data object Idle : PasswordState()
    data class Error(val message: String) : PasswordState()
}

enum class PasswordInput {
    CURRENT, NEW, RETYPE
}