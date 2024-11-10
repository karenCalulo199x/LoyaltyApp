package com.kcals.loyaltyapp.model.network

import com.kcals.loyaltyapp.model.response.ProfileResponse
import retrofit2.http.GET

interface ApiService {
    @GET("profile.json")
    suspend fun fetchProfile(): ProfileResponse
}