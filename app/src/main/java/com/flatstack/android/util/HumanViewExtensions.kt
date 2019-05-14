package com.flatstack.android.util

import com.flatstack.android.R
import retrofit2.HttpException
import java.io.IOException
import java.net.HttpURLConnection.HTTP_BAD_METHOD
import java.net.HttpURLConnection.HTTP_BAD_REQUEST
import java.net.HttpURLConnection.HTTP_CONFLICT
import java.net.HttpURLConnection.HTTP_FORBIDDEN
import java.net.HttpURLConnection.HTTP_NOT_FOUND
import java.net.HttpURLConnection.HTTP_NOT_MODIFIED
import java.net.HttpURLConnection.HTTP_SERVER_ERROR
import java.net.HttpURLConnection.HTTP_UNAUTHORIZED

fun Throwable.userMessage(resourceManager: StringResource) = when (this) {
    is HttpException -> when (this.code()) {
        HTTP_NOT_MODIFIED -> resourceManager.getString(R.string.not_modified_error)
        HTTP_BAD_REQUEST -> resourceManager.getString(R.string.bad_request_error)
        HTTP_UNAUTHORIZED -> resourceManager.getString(R.string.unauthorized_error)
        HTTP_FORBIDDEN -> resourceManager.getString(R.string.forbidden_error)
        HTTP_NOT_FOUND -> resourceManager.getString(R.string.not_found_error)
        HTTP_BAD_METHOD -> resourceManager.getString(R.string.method_not_allowed_error)
        HTTP_CONFLICT -> resourceManager.getString(R.string.conflict_error)
        HTTP_SERVER_ERROR -> resourceManager.getString(R.string.server_error_error)
        else -> resourceManager.getString(R.string.unknown_error)
    }
    is IOException -> resourceManager.getString(R.string.network_error)
    else -> resourceManager.getString(R.string.unknown_error)
}