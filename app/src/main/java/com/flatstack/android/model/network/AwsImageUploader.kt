package com.flatstack.android.model.network

import com.flatstack.android.graphql.mutation.PresignMutation
import com.flatstack.android.type.ImageUploader
import okhttp3.Call
import okhttp3.MultipartBody
import retrofit2.http.*

interface AwsImageUploader {

    @Multipart
    @POST
    suspend fun uploadImage(
        @Part file: MultipartBody.Part,
        @Url url: String
    ): ImageUploader
}