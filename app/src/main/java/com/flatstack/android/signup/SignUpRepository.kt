package com.flatstack.android.signup

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Input
import com.apollographql.apollo.coroutines.toDeferred
import com.flatstack.android.graphql.mutation.SignUpMutation
import com.flatstack.android.model.entities.Session
import com.flatstack.android.profile.AuthorizationModel
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class SignUpRepository(
    private val apolloClient: ApolloClient,
    private val authorizationModel: AuthorizationModel
) {
    fun signUp(email: String, firstName: String?, lastName: String?, password: String) = flow {
        emit(
            apolloClient.mutate(
                SignUpMutation(
                    email = email,
                    firstName = Input.fromNullable(firstName),
                    lastName = Input.fromNullable(lastName),
                    password = password
                )
            ).toDeferred().await()
        )
    }
        .map {
            it.data?.signup?.fragments?.sessionFragment?.accessToken?.let { accessToken ->
                Session(accessToken)
            }
        }
        .onEach { it?.let { authorizationModel.setSession(it) }}
}