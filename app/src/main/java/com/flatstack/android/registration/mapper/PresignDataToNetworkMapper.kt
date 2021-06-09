package com.flatstack.android.registration.mapper

import com.flatstack.android.registration.entities.request.PresignDataRequest
import com.flatstack.android.type.PresignDataInput

class PresignDataToNetworkMapper: (PresignDataRequest) -> PresignDataInput {
    override fun invoke(request: PresignDataRequest): PresignDataInput =
        request.run {
            PresignDataInput(
                filename = fileName,
                type = type
            )
        }
}
