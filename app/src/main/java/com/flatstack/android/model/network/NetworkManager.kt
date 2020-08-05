package com.flatstack.android.model.network

import kotlinx.coroutines.suspendCancellableCoroutine
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.IOException

class NetworkManager(private val okHttpClient: OkHttpClient) {

    suspend fun uploadImageToAws(url: String, fields: Map<String, String>, file: File): Boolean =
        suspendCancellableCoroutine { cancellableContinuation ->
            val requestBody = MultipartBody.Builder().setType(MultipartBody.FORM)
            fields.forEach {
                requestBody.addFormDataPart(it.key, it.value)
            }
            requestBody.addFormDataPart(
                FORM_DATA_KEY,
                file.name,
                file.asRequestBody(contentType = REQUEST_BODY_CONTENT_TYPE.toMediaTypeOrNull())
            )
            val request = Request.Builder()
                .url(url)
                .method(REQUEST_METHOD, requestBody.build())
                .build()
            okHttpClient
                .newCall(request)
                .enqueue(object : Callback {
                    override fun onFailure(call: Call, e: IOException) {
                        cancellableContinuation.resume(false) {}
                    }

                    override fun onResponse(call: Call, response: Response) {
                        cancellableContinuation.resume(true) {}
                    }
                })
        }

    companion object {
        const val FORM_DATA_KEY = "file"
        const val REQUEST_BODY_CONTENT_TYPE = "application/octet-stream"
        const val REQUEST_METHOD = "POST"
    }
}
