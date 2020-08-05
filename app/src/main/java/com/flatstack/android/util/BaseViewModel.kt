package com.flatstack.android.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    private val shouldShowProgressMutableData = MutableLiveData<Boolean>()
    val shouldShowProgressData : LiveData<Boolean> = shouldShowProgressMutableData

    suspend fun <T> runSafe(
        operation: suspend () -> T,
        onSuccess: (T) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        runCatching {
            showProgress()
            operation()
        }
            .onSuccess(onSuccess)
            .onFailure(onError)
        hideProgress()
    }

    private fun showProgress() {
        shouldShowProgressMutableData.value = true
    }

    private fun hideProgress() {
        shouldShowProgressMutableData.value = false
    }
}
