package com.kcals.loyaltyapp.view.fragment

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.kcals.loyaltyapp.base.BaseFragment
import com.kcals.loyaltyapp.databinding.FragmentPasswordBinding
import com.kcals.loyaltyapp.viewmodel.PasswordInput
import com.kcals.loyaltyapp.viewmodel.PasswordState
import com.kcals.loyaltyapp.viewmodel.PasswordViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PasswordFragment : BaseFragment<FragmentPasswordBinding>() {
    private val viewModel: PasswordViewModel by viewModels()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentPasswordBinding {
        return FragmentPasswordBinding.inflate(inflater, container, false)
    }

    override fun initViews() {
        initViewModels()
        binding.apply {

            etCurrentPassword.removeEmptyError(PasswordInput.CURRENT)
            etNewPassword.removeEmptyError(PasswordInput.NEW)
            etRetypePassword.removeEmptyError(PasswordInput.RETYPE)

            saveBtn.setOnClickListener {
                val current = etCurrentPassword.text.toString()
                val new = etNewPassword.text.toString()
                val retype = etRetypePassword.text.toString()
                viewModel.apply {
                    verifyCurrentPass(current)
                    verifyNewPass(new)
                    verifyRetypePass(new, retype)
                }
            }
        }
    }

    private fun initViewModels() {
        viewModel.apply {
            viewModelScope.launch {
                currentPassState.collect { state ->
                    statePasswordBinding(state, binding.etCurrentPasswordLayout)
                }
            }
            viewModelScope.launch {
                newPassState.collect { state ->
                    statePasswordBinding(state, binding.etNewPasswordLayout)
                }
            }
            viewModelScope.launch {
                reTypePassState.collect { state ->
                    statePasswordBinding(state, binding.etRetypePasswordLayout)
                }
            }
        }
    }

    private fun statePasswordBinding(state: PasswordState, view: TextInputLayout) {
        when (state) {
            is PasswordState.Success -> view.error = null
            is PasswordState.Idle -> view.error = null
            is PasswordState.Error -> view.error = state.message
        }
    }

    private fun TextInputEditText.removeEmptyError(passInput: PasswordInput) {
        this.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!s.isNullOrEmpty()) viewModel.fieldFilledUp(passInput)
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }


}