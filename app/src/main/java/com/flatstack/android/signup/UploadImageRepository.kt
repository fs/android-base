package com.flatstack.android.signup

import com.flatstack.android.graphql.mutation.PresignMutation
import com.flatstack.android.model.network.AwsImageUploader
import com.flatstack.android.type.ImageUploader
import kotlinx.coroutines.flow.flow

class UploadImageRepository(
    private val imageUploader: AwsImageUploader
) {

}