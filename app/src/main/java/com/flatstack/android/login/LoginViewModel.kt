package com.flatstack.android.login

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.flatstack.android.R
import com.flatstack.android.model.entities.Resource
import com.flatstack.android.model.entities.Session
import com.flatstack.android.util.StringResource
import com.flatstack.android.util.toLiveData

class LoginViewModel(
    private val loginRepository: LoginRepository,
    private val stringResource: StringResource
) : ViewModel() {

    private val login = MutableLiveData<LoginId>()

    val loginResource: LiveData<Resource<Session>> = Transformations.switchMap(login) {
        it.ifExists(exist = { username, password ->
            loginRepository.login(username, password)
        }, empty = {
            emptyLoginResource()
        })
    }

    override fun onCleared() {
        loginRepository.onDestroy()
    }

    fun login(username: String, password: String) {
        login.postValue(LoginId(username, password))
    }

    @VisibleForTesting
    fun emptyLoginResource() =
        Resource.error<Session>(stringResource.getString(R.string.empty_error)).toLiveData()

    data class LoginId(
        val username: String,
        val password: String
    ) {
        fun <T> ifExists(exist: (String, String) -> LiveData<T>, empty: () -> LiveData<T>): LiveData<T> =
            if (username.isBlank() || password.isBlank()) {
                empty()
            } else {
                exist(username, password)
            }
    }
}
