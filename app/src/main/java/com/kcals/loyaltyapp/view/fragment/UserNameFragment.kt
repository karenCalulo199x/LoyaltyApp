package com.kcals.loyaltyapp.view.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.kcals.loyaltyapp.base.BaseFragment
import com.kcals.loyaltyapp.databinding.FragmentUserNameBinding
import com.kcals.loyaltyapp.model.response.ProfileResponse
import com.kcals.loyaltyapp.viewmodel.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlin.getValue

@AndroidEntryPoint
class UserNameFragment : BaseFragment<FragmentUserNameBinding>() {
    private val viewModel: ProfileViewModel by viewModels()
    private var profileResponse: ProfileResponse? = null
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentUserNameBinding {
        return FragmentUserNameBinding.inflate(inflater, container, false)
    }

    override fun initViews() {
        binding.apply {
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.profileData.collect {
                    firstNameTied.setText(it?.firstName)
                    lastNameTied.setText(it?.lastName)
                    profileResponse = it
                }
            }
            viewModel.fetchProfileDetails()
            saveBtn.setOnClickListener {
                val fName = firstNameTied.text.toString()
                val lName = lastNameTied.text.toString()
                viewModel.updateProfileName(fName, lName, profileResponse)
                findNavController().popBackStack()
            }
        }
    }
}