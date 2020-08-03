package com.flatstack.android.di.modules

import com.flatstack.android.profile.AuthorizationModel
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor(private val authorizationModel: AuthorizationModel) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        request = request.newBuilder()
            .addHeader(AUTH_HEADER, getAccessToken())
            .build()

        return chain.proceed(request)
    }

    private fun getAccessToken(): String = runBlocking { "Bearer ${authorizationModel.getToken()}" }

    companion object {
        const val AUTH_HEADER: String = "Authorization"
    }
}
