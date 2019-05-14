package com.flatstack.android.login

import com.flatstack.android.login.entities.LoginRequest
import com.flatstack.android.login.entities.LoginResponse
import com.flatstack.android.model.entities.Session
import com.flatstack.android.model.network.ApiResponse
import com.flatstack.android.model.network.IApi
import com.flatstack.android.model.network.NetworkBoundResource
import com.flatstack.android.model.network.errors.ErrorHandler
import com.flatstack.android.profile.AuthorizationModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch

class LoginRepository(
    private val api: IApi,
    private val authorizationModel: AuthorizationModel,
    private val errorHandler: ErrorHandler
) : CoroutineScope {
    override val coroutineContext = SupervisorJob() + Dispatchers.IO

    fun login(
        username: String,
        password: String
    ) = object : NetworkBoundResource<Session, LoginResponse>(coroutineContext, errorHandler) {
        override suspend fun createCallAsync(): Deferred<ApiResponse<LoginResponse>> =
            api.loginAsync(LoginRequest(username, password))

        override suspend fun saveCallResult(item: LoginResponse) =
            authorizationModel.setSession(item.session)

        override suspend fun loadFromDb() = authorizationModel.getSession()
    }.asLiveData()

    fun unAuthorize() {
        launch {
            authorizationModel.unAuthorize()
        }
    }

    fun onDestroy() {
        coroutineContext.cancelChildren()
    }
}
