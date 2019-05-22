package com.flatstack.android.model.network

import com.google.gson.annotations.SerializedName

data class ErrorBody(
    @SerializedName("status") val status: Int,
    @SerializedName("error") val error: String
)
