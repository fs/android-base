package com.flatstack.android.registration

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.coroutines.toDeferred
import com.flatstack.android.graphql.mutation.RegisterMutation
import com.flatstack.android.model.entities.Session
import com.flatstack.android.registration.entities.request.RegisterRequest
import com.flatstack.android.registration.mapper.RegisterRequestToNetworkMapper

class RegistrationRepository(
    private val apolloClient: ApolloClient,
    private val mapSessionFromRegistration: SessionFromRegistrationMapper,
    private val mapRegisterInputToNetwork: RegisterRequestToNetworkMapper
) {

    suspend fun register(request: RegisterRequest) =
        apolloClient.mutate(RegisterMutation(mapRegisterInputToNetwork(request)))
            .toDeferred()
            .await()
            .data?.signup
            ?.run(mapSessionFromRegistration) ?: Session("")
}
