package com.flatstack.android.signup

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
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
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okio.BufferedSink
import okio.source
import java.io.File

class SignUpViewModel(
    private val signUpRepository: SignUpRepository,
    private val stringResource: StringResource,
    private val awsImageUploader: AwsImageUploader,
    private val context: Context
) : ViewModel() {

    private val signUp = MutableLiveData<SignUpData>()

    val signUpResource: LiveData<Resource<Session>> =
        Transformations.switchMap(signUp) { signUpData ->
            uploadImage(signUpData.avatar, signUpData.fileUri)
            signUpData.ifExists(exist = { email, password ->
//                signUpRepository.signUp(
//                    email = email,
//                    password = password,
//                    firstName = signUpData.firstName,
//                    lastName = signUpData.lastName,
//                    avatar = signUpData.avatar
//                )
//                    .map { Resource.success(it) }
//                    .onStart { emit(Resource.loading()) }
//                    .catch { emit(Resource.error(stringResource.getString(R.string.unknown_error))) }
//                    .asLiveData(viewModelScope.coroutineContext)
                emptySignUpResource()
            }, empty = {
                emptySignUpResource()
            })
        } ?: run {
            emptySignUpResource()
        }


    private fun uploadImage(avatar: ImageUploader?, fileUri: Uri?) {
        var imageUploader: ImageUploader? = null
        avatar?.metadata?.let {
            viewModelScope.launch {
                signUpRepository.presign(it.filename, it.mimeType)
                    .flowOn(Dispatchers.IO)
                    .map {
                        it?.let { presignData ->
                            fileUri?.let { fileUri ->
                                context.contentResolver.getType(fileUri)?.let { mimetype ->
                                    val file = File(fileUri.path)
                                    val requestBody =
                                        InputStreamRequestBody(context.contentResolver, fileUri)
                                    val filePart = context.contentResolver.readAsRequestBody(fileUri)

                                    val part = MultipartBody.Part.createFormData(mimetype, file.name, filePart)
                                    val query = mutableMapOf<String, String>().also { query ->
                                        presignData.fields.forEach {field ->
                                            query.put(field.key, field.value)
                                        }
                                    }


                                    val bodyBuilder = MultipartBody.Builder()
                                    presignData.fields.forEach {
                                        bodyBuilder.addFormDataPart(it.key, it.value)
                                    }

                                    awsImageUploader.uploadImage(
                                        url = presignData.url,
                                        contentType = mimetype,
                                        query = query,
                                        file = part
                                    )
                                }
                            }
                        }
                    }.collect()
            }
        }
    }

    private fun ContentResolver.readAsRequestBody(uri: Uri) =
        object: RequestBody() {
            override fun contentType(): MediaType? =
                this@readAsRequestBody.getType(uri)?.toMediaTypeOrNull()

            override fun writeTo(sink: BufferedSink) {
                this@readAsRequestBody.openInputStream(uri)?.source()?.use(sink::writeAll)
            }

            override fun contentLength(): Long =
                this@readAsRequestBody.query(uri, null, null, null, null)?.use { cursor ->
                    val sizeColumnIndex: Int = cursor.getColumnIndex(OpenableColumns.SIZE)
                    cursor.moveToFirst()
                    cursor.getLong(sizeColumnIndex)
                } ?: super.contentLength()
        }

    fun signUp(
        email: String,
        firstName: String?,
        lastName: String?,
        password: String,
        avatar: ImageUploader?,
        fileUri: Uri?
    ) {
        signUp.postValue(SignUpData(email, firstName, lastName, password, avatar, fileUri))
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
        val fileUri: Uri?
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
