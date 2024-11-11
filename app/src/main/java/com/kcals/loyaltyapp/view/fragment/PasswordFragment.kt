package com.kcals.loyaltyapp.view.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import com.kcals.loyaltyapp.base.BaseFragment
import com.kcals.loyaltyapp.databinding.FragmentPasswordBinding


class PasswordFragment : BaseFragment<FragmentPasswordBinding>() {
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentPasswordBinding {
        return FragmentPasswordBinding.inflate(inflater, container, false)
    }

    override fun initViews() {
        binding.apply {

        }
    }


}