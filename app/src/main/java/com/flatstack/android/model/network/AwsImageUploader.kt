package com.flatstack.android.model.network

import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface AwsImageUploader {

    @Multipart
    @PUT
    @Headers("x-amz-acl:public-read")
    suspend fun uploadImage(
        @Url url: String,
        @Header("Content-type") contentType: String,
        @QueryMap query: Map<String, String>,
        @Part file: MultipartBody.Part
    ): Response<Unit>
}