package com.flatstack.android.profile

import com.flatstack.android.model.db.daos.ProfileDao
import com.flatstack.android.model.network.ApiResponse
import com.flatstack.android.model.network.IApi
import com.flatstack.android.model.network.NetworkBoundResource
import com.flatstack.android.model.network.errors.ErrorHandler
import com.flatstack.android.profile.entities.Profile
import com.flatstack.android.profile.entities.ProfileResponse
import kotlinx.coroutines.*

class ProfileRepository(
    private val profileDao: ProfileDao,
    private val iApi: IApi,
    private val errorHandler: ErrorHandler,
    private val authorizationModel: AuthorizationModel
) : CoroutineScope {
    override val coroutineContext = SupervisorJob() + Dispatchers.IO

    fun loadProfile() =
        object : NetworkBoundResource<Profile, ProfileResponse>(coroutineContext, errorHandler) {
            override suspend fun saveCallResult(item: ProfileResponse) =
                profileDao.insertProfileAndBooks(item.profile)

            override suspend fun loadFromDb(): Profile? = profileDao.getProfileWithBooks()

            override suspend fun createCallAsync(): Deferred<ApiResponse<ProfileResponse>> =
                iApi.loadUserAsync(authorizationModel.getToken())

        }

    fun onDestroy() {
        coroutineContext.cancelChildren()
    }
}
