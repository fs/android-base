package com.flatstack.android.registration.mapper

import com.apollographql.apollo.api.Input
import com.flatstack.android.registration.entities.request.RegisterRequest
import com.flatstack.android.type.ImageUploader
import com.flatstack.android.type.ImageUploaderMetadata
import com.flatstack.android.type.SignUpInput

class RegisterRequestToNetworkMapper : (RegisterRequest) -> SignUpInput {
    override fun invoke(request: RegisterRequest): SignUpInput =
        request.run {
            SignUpInput(
                email = email,
                password = password,
                firstName = Input.optional(firstName),
                lastName = Input.optional(lastName),
                avatar = Input.optional(avatar?.run {
                    ImageUploader(
                        id = id,
                        storage = Input.optional(storage),
                        metadata = metadata.run {
                            ImageUploaderMetadata(
                                size = size,
                                filename = fileName,
                                mimeType = mimeType
                            )
                        }
                    )
                })
            )
        }
}
