package com.kcals.loyaltyapp.model.repository

import com.kcals.loyaltyapp.model.network.ApiService
import com.kcals.loyaltyapp.model.response.ProfileResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DataRepository @Inject constructor(private val apiService: ApiService) {

    fun fetchProfile(): Flow<ProfileResponse> = flow {
        val response = apiService.fetchProfile()
        emit(response)
    }
}