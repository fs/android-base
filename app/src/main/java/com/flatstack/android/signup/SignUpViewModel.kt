package com.flatstack.android.signup

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.*
import com.flatstack.android.R
import com.flatstack.android.model.entities.Resource
import com.flatstack.android.model.entities.Session
import com.flatstack.android.type.ImageUploader
import com.flatstack.android.util.StringResource
import com.flatstack.android.util.toLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

class SignUpViewModel(
    private val signUpRepository: SignUpRepository,
    private val stringResource: StringResource
) : ViewModel() {

    private val signUp = MutableLiveData<SignUpData>()

    val signUpResource: LiveData<Resource<Session>> = Transformations.switchMap(signUp) {
        it.ifExists(exist = { email, password ->
            signUpRepository.signUp(email, it.firstName, it.lastName, password, it.avatar)
                .flowOn(Dispatchers.IO)
                .map { Resource.success(it) }
                .onStart { emit(Resource.loading()) }
                .catch { emit(Resource.error(stringResource.getString(R.string.unknown_error))) }
                .asLiveData(viewModelScope.coroutineContext)
        }, empty = {
            emptySignUpResource()
        })
    }

    fun signUp(email: String, firstName: String?, lastName: String?, password: String, avatar: ImageUploader?) {
        signUp.postValue(SignUpData(email, firstName, lastName, password, avatar))
    }

    @VisibleForTesting
    fun emptySignUpResource() =
        Resource.error<Session>(stringResource.getString(R.string.empty_error)).toLiveData()

    data class SignUpData(
        val email: String,
        val firstName: String?,
        val lastName: String?,
        val password: String,
        val avatar: ImageUploader?
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