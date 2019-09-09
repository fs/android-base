package com.flatstack.android.model.network

import com.flatstack.android.login.entities.LoginRequest
import com.flatstack.android.login.entities.LoginResponse
import com.flatstack.android.profile.entities.ProfileResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.*

interface IApi {
    @Headers("Accept: application/json", "Content-Type: application/json")
    @POST("/v1/sessions")
    fun loginAsync(
        @Body loginRequest: LoginRequest
    ): Deferred<ApiResponse<LoginResponse>>

    @Headers("Content-Type: application/json; charset=utf-8")
    @GET("/v1/profile")
    fun loadUserAsync(
        @Header("Authorization") token: String
    ): Deferred<ApiResponse<ProfileResponse>>
}
