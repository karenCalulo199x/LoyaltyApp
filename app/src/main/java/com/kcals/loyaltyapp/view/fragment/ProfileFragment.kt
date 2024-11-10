package com.kcals.loyaltyapp.view.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.kcals.loyaltyapp.R
import com.kcals.loyaltyapp.base.BaseFragment
import com.kcals.loyaltyapp.databinding.FragmentProfileBinding
import com.kcals.loyaltyapp.viewmodel.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>() {
    private val viewModel: ProfileViewModel by viewModels()

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
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.profileData.collect {
                    it?.let {
                        nameTv.text = getString(R.string.full_name, it.firstName, it.lastName)
                    }
                }
            }
            viewModel.fetchProfileDetails()
        }
    }
}