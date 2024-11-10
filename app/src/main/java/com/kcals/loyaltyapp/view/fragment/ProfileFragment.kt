package com.kcals.loyaltyapp.view.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.kcals.loyaltyapp.R
import com.kcals.loyaltyapp.base.BaseFragment
import com.kcals.loyaltyapp.databinding.FragmentProfileBinding

class ProfileFragment : BaseFragment<FragmentProfileBinding>() {
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentProfileBinding {
        return FragmentProfileBinding.inflate(inflater, container, false)
    }

    override fun initViews() {
        binding.apply {
            nameTv.setOnClickListener {
                findNavController().navigate(R.id.action_profile_fragment_nav_to_name_fragment_nav)
            }
        }
    }
}