package com.kcals.loyaltyapp.model.response

import com.google.gson.annotations.SerializedName

data class ProfileResponse(
    val id: Int,
    val avatar: String?,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("phone_no")
    val phoneNo: String?,
    @SerializedName("is_verified")
    val isVerified: Boolean? = false,
    @SerializedName("point_count")
    val points: Int?,
    val tier: String?
)
