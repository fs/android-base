package com.flatstack.android.signup

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.*
import com.flatstack.android.R
import com.flatstack.android.model.entities.Resource
import com.flatstack.android.model.entities.Session
import com.flatstack.android.model.network.AwsImageUploader
import com.flatstack.android.type.ImageUploader
import com.flatstack.android.util.StringResource
import com.flatstack.android.util.toLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import okhttp3.FormBody
import okhttp3.Headers.Companion.toHeaders
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class SignUpViewModel(
    private val signUpRepository: SignUpRepository,
    private val stringResource: StringResource,
    private val awsImageUploader: AwsImageUploader
) : ViewModel() {

    private val signUp = MutableLiveData<SignUpData>()

    val signUpResource: LiveData<Resource<Session>> =
        Transformations.switchMap(signUp) { signUpData ->
            uploadImage(signUpData.avatar, signUpData.file)?.let { avatar ->
                signUpData.ifExists(exist = { email, password ->
                    signUpRepository.signUp(
                        email = email,
                        password = password,
                        firstName = signUpData.firstName,
                        lastName = signUpData.lastName,
                        avatar = avatar
                    )
                        .map { Resource.success(it) }
                        .onStart { emit(Resource.loading()) }
                        .catch { emit(Resource.error(stringResource.getString(R.string.unknown_error))) }
                        .asLiveData(viewModelScope.coroutineContext)
                }, empty = {
                    emptySignUpResource()
                })
            } ?: run {
                emptySignUpResource()
            }
        }

    private fun uploadImage(avatar: ImageUploader?, file: File?): ImageUploader? {
        var imageUploader: ImageUploader? = null
        avatar?.metadata?.let {
            viewModelScope.launch {
                signUpRepository.presign(it.filename, it.mimeType)
                    .flowOn(Dispatchers.IO)
                    .map {
                        it?.let { presignData ->
                            file?.let { file ->
                                val fields = mutableMapOf<String, String>()
                                presignData.fields.forEach { field ->
                                    fields[field.key] = field.value
                                }
                                awsImageUploader.uploadImage(
                                    url = presignData.url,
                                    body = fields.toMap(),
                                    file = MultipartBody.Part.createFormData(
                                        "file",
                                        file.name,
                                        RequestBody.create(avatar.metadata.mimeType.toMediaTypeOrNull(), file)
                                    )
                                )
                            }
                        }
                    }
                    .collect {
                        imageUploader = it
                    }
            }
        }
        return imageUploader
    }

    fun signUp(
        email: String,
        firstName: String?,
        lastName: String?,
        password: String,
        avatar: ImageUploader?,
        file: File?
    ) {
        signUp.postValue(SignUpData(email, firstName, lastName, password, avatar, file))
    }

    @VisibleForTesting
    fun emptySignUpResource() =
        Resource.error<Session>(stringResource.getString(R.string.empty_error)).toLiveData()

    data class SignUpData(
        val email: String,
        val firstName: String?,
        val lastName: String?,
        val password: String,
        val avatar: ImageUploader?,
        val file: File?
    ) {
        fun <T> ifExists(
            exist: (String, String) -> LiveData<T>,
            empty: () -> LiveData<T>
        ): LiveData<T> =
            if (email.isBlank() || password.isBlank())
                empty()
            else
                exist(email, password)
    }
}
