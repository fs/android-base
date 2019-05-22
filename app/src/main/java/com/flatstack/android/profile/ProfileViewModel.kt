package com.flatstack.android.profile

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import com.flatstack.android.login.LoginRepository

class ProfileViewModel(
    private val profileRepository: ProfileRepository,
    private val loginRepository: LoginRepository
) : ViewModel() {

    @VisibleForTesting
    val profileBoundResource = profileRepository.loadProfile()
    val profileResponse = profileBoundResource.asLiveData()

    override fun onCleared() {
        profileRepository.onDestroy()
        loginRepository.onDestroy()
    }

    fun updateProfile() {
        profileBoundResource.fetchFromNetwork()
    }

    fun logout() {
        loginRepository.unAuthorize()
    }
}
