package com.flatstack.android.registration.entities.request

import java.io.File

data class       ImageUploadRequest(
    val id: String,
    val storage: String = "cache",
    val metadata: MetadataRequest
) {
    var file: File? = null
}
