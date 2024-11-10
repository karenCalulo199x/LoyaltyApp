package com.kcals.loyaltyapp.view.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import com.kcals.loyaltyapp.base.BaseFragment
import com.kcals.loyaltyapp.databinding.FragmentUserNameBinding

class UserNameFragment : BaseFragment<FragmentUserNameBinding>() {
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentUserNameBinding {
        return FragmentUserNameBinding.inflate(inflater, container, false)
    }

    override fun initViews() {
        binding.apply {

        }
    }

}