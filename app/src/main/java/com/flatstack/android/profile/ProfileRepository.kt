package com.flatstack.android.profile

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.coroutines.toDeferred
import com.flatstack.android.graphql.query.GetUserQuery
import com.flatstack.android.model.db.daos.ProfileDao
import com.flatstack.android.model.network.NetworkBoundResource
import com.flatstack.android.model.network.errors.ErrorHandler
import com.flatstack.android.profile.entities.Profile
import kotlinx.coroutines.*

class ProfileRepository(
    private val apolloClient: ApolloClient,
    private val profileDao: ProfileDao,
    private val errorHandler: ErrorHandler
) : CoroutineScope {
    override val coroutineContext = SupervisorJob() + Dispatchers.IO

    fun loadProfile() =
        object : NetworkBoundResource<Profile, GetUserQuery.Data>(coroutineContext, errorHandler) {
            override suspend fun createCallAsync(): Deferred<Response<GetUserQuery.Data>> =
                apolloClient.query(GetUserQuery()).toDeferred()

            override suspend fun saveCallResult(item: GetUserQuery.Data?) {
                profileDao.insertUserProfile(ProfileMapper.mapProfile(item?.me))
            }

            override suspend fun loadFromDb(): Profile? = profileDao.getProfile()
        }

    fun onDestroy() {
        coroutineContext.cancelChildren()
    }
}
