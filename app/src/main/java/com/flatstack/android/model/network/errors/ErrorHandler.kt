package com.flatstack.android.model.network.errors

import androidx.annotation.VisibleForTesting
import com.apollographql.apollo.api.BigDecimal
import com.apollographql.apollo.api.Error
import com.flatstack.android.R
import com.flatstack.android.Router
import com.flatstack.android.profile.AuthorizationModel
import com.flatstack.android.util.StringResource
import kotlinx.coroutines.runBlocking
import java.net.HttpURLConnection

class ErrorHandler(
    private val stringResource: StringResource,
    private val authorizationModel: AuthorizationModel,
    private val router: Router
) {

    fun proceed(error: Error?): String = error?.let {
        mapToStatus(it).let { code ->
            when (code) {
                HttpURLConnection.HTTP_UNAUTHORIZED -> runBlocking { unAuthorize() }
            }
            if (error.message.isEmpty()) {
                userMessage(it)
            } else {
                error.message
            }
        }
    } ?: stringResource.getString(R.string.unknown_error)

    @VisibleForTesting
    suspend fun unAuthorize() {
        authorizationModel.unAuthorize()
        router.login(clearStack = true)
    }

    private fun userMessage(error: Error) = when (mapToStatus(error)) {
        HttpURLConnection.HTTP_NOT_MODIFIED -> stringResource.getString(R.string.not_modified_error)
        HttpURLConnection.HTTP_BAD_REQUEST -> stringResource.getString(R.string.bad_request_error)
        HttpURLConnection.HTTP_UNAUTHORIZED -> stringResource.getString(R.string.unauthorized_error)
        HttpURLConnection.HTTP_FORBIDDEN -> stringResource.getString(R.string.forbidden_error)
        HttpURLConnection.HTTP_NOT_FOUND -> stringResource.getString(R.string.not_found_error)
        HttpURLConnection.HTTP_BAD_METHOD -> stringResource.getString(R.string.method_not_allowed_error)
        HttpURLConnection.HTTP_CONFLICT -> stringResource.getString(R.string.conflict_error)
        HttpURLConnection.HTTP_INTERNAL_ERROR -> stringResource.getString(R.string.server_error_error)
        else -> ""
    }

    private fun mapToStatus(error: Error) =
        ((error.customAttributes["extensions"] as LinkedHashMap<*, *>)["status"] as BigDecimal).intValueExact()
}
