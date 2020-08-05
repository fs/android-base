package com.flatstack.android.registration.entities.request

data class RegisterRequest(
    val avatar: ImageUploadRequest?,
    val email: String,
    val firstName: String?,
    val lastName: String?,
    val password: String
)
