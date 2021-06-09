package com.flatstack.android.registration

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.flatstack.android.R
import com.flatstack.android.Router
import com.flatstack.android.profile.AuthorizationModel
import com.flatstack.android.registration.entities.request.ImageUploadRequest
import com.flatstack.android.registration.entities.request.RegisterRequest
import com.flatstack.android.registration.entities.response.PresignData
import com.flatstack.android.util.BaseViewModel
import com.flatstack.android.util.StringResource
import kotlinx.coroutines.launch

class RegistrationViewModel(
    private val registrationRepository: RegistrationRepository,
    private val stringResource: StringResource,
    private val authorizationModel: AuthorizationModel,
    private val presignDataRepository: PresignDataRepository,
    private val router: Router
) : BaseViewModel() {

    private val errorMutableData = MutableLiveData<String>()
    private val navigateToProfileMutableData = MutableLiveData<Unit>()
    private var presignData : PresignData? = null

    val errorData : LiveData<String> = errorMutableData
    val navigateToProfileData : LiveData<Unit> = navigateToProfileMutableData

    fun register(
        avatar: ImageUploadRequest?,
        email: String,
        firstName: String?,
        lastName: String?,
        password: String,
        confirmPassword: String
    ) {
        if (email.isNotEmpty() && password.isNotEmpty() && password == confirmPassword) {
            var imageUploadRequest: ImageUploadRequest? = null
            viewModelScope.launch {
                avatar?.let{ avatar ->
                    presignAvatar(avatar)
                    presignData?.let { presignData ->
                        avatar.file?.let { file ->
                                presignDataRepository.uploadImageToAws(presignData.url, presignData.fields, file)

                                imageUploadRequest = avatar.copy(
                                    id = getImageId(presignData.fields)
                                )
                            }
                        }
                } ?: run {
                    imageUploadRequest = avatar?.copy()
                }
                signUp(RegisterRequest(imageUploadRequest, email, firstName, lastName, password))
            }
        } else {
            showError(stringResource.getString(R.string.invalid_credentials))
        }
    }

    private fun getImageId(fields: Map<String, String>) =
        fields[ARG_KEY]?.split("/")?.last() ?: ""

    private suspend fun signUp(registerRequest: RegisterRequest) =
        runSafe(
            operation = {
                registrationRepository.register(registerRequest)
            },
            onSuccess = {
                viewModelScope.launch {
                    authorizationModel.setSession(it)
                }
                navigateToProfile()
            },
            onError = {
                showError(it.message)
            }
        )

    private suspend fun presignAvatar(avatar: ImageUploadRequest) {
        runSafe(
            operation = {
                presignDataRepository.presignData(
                    avatar.metadata.fileName,
                    avatar.metadata.mimeType
                )
            },
            onSuccess = {
                presignData = it
            },
            onError = {
                showError(it.message)
            }
        )
    }

    private fun navigateToProfile() {
        navigateToProfileMutableData.value = Unit
    }

    private fun showError(error: String?) {
        errorMutableData.value = error ?: stringResource.getString(R.string.something_went_wrong)
    }

    companion object {
        const val ARG_KEY = "key"
    }
}
