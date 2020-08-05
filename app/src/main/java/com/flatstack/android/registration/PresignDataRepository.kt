package com.flatstack.android.registration

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.coroutines.toDeferred
import com.flatstack.android.graphql.mutation.PresignAvatarMutation
import com.flatstack.android.model.network.NetworkManager
import com.flatstack.android.registration.entities.request.PresignDataRequest
import com.flatstack.android.registration.mapper.PresignDataFromNetworkMapper
import com.flatstack.android.registration.mapper.PresignDataToNetworkMapper
import java.io.File

class PresignDataRepository(
    private val apolloClient: ApolloClient,
    private val networkManager: NetworkManager,
    private val mapPresignDataToNetwork: PresignDataToNetworkMapper,
    private val mapPresignDataFromNetwork: PresignDataFromNetworkMapper
) {
    suspend fun presignData(fileName: String, type: String) =
        apolloClient.mutate(
            PresignAvatarMutation(
                PresignDataRequest(fileName, type).run(mapPresignDataToNetwork)
            )
        )
                .toDeferred()
                .await()
                .data?.presignData
                ?.run(mapPresignDataFromNetwork)

    suspend fun uploadImageToAws(url: String, fields: Map<String, String>, file: File) : Boolean =
        networkManager.uploadImageToAws(url, fields, file)
}
