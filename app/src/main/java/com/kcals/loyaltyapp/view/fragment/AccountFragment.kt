package com.kcals.loyaltyapp.view.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.kcals.loyaltyapp.R
import com.kcals.loyaltyapp.base.BaseFragment
import com.kcals.loyaltyapp.databinding.FragmentAccountBinding

class AccountFragment : BaseFragment<FragmentAccountBinding>() {
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentAccountBinding {
        return FragmentAccountBinding.inflate(inflater, container, false)
    }

    override fun initViews() {
        binding.apply {
            profileTv.setOnClickListener {
                findNavController().navigate(R.id.action_account_fragment_nav_to_profile_fragment_nav)
            }
            passwordTv.setOnClickListener {
                findNavController().navigate(R.id.action_account_fragment_nav_to_passwordFragment)
            }

        }
    }
}