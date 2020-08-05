package com.flatstack.android.registration.mapper

import com.flatstack.android.graphql.mutation.PresignAvatarMutation
import com.flatstack.android.registration.entities.response.PresignData

class PresignDataFromNetworkMapper: (PresignAvatarMutation.PresignData) -> PresignData {
    override fun invoke(response: PresignAvatarMutation.PresignData): PresignData =
        response.run {
            PresignData(
                fields = fields.map { it.key to it.value }.toMap(),
                url = url
            )
        }
}
