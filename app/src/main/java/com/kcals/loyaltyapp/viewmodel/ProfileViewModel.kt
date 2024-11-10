package com.kcals.loyaltyapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kcals.loyaltyapp.model.repository.DataRepository
import com.kcals.loyaltyapp.model.response.ProfileResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val repository: DataRepository) : ViewModel() {
    private var mProfileData = MutableStateFlow<ProfileResponse?>(null)
    val profileData: StateFlow<ProfileResponse?> get() = mProfileData

    fun fetchProfileDetails() {
        viewModelScope.launch {
            repository.fetchProfile().collect {
                mProfileData.value = it
                updateProfileName(it.firstName, it.lastName, it)
            }
        }
    }

    fun updateProfileName(fName: String, lName: String, data: ProfileResponse? = null) {
        viewModelScope.launch {
            data?.let {
                mProfileData.value = ProfileResponse(
                    it.id,
                    it.avatar,
                    fName,
                    lName,
                    it.phoneNo,
                    it.isVerified,
                    it.points,
                    it.tier
                )
            }
        }
    }
}