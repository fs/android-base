package com.flatstack.android.util

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.flatstack.android.model.entities.Resource
import com.flatstack.android.model.entities.Status

fun <T : Any> LiveData<Resource<T>>.observeBy(
    owner: LifecycleOwner,
    onNext: (T) -> Unit = {},
    onError: (String) -> Unit = {},
    onLoading: (Boolean) -> Unit = {},
    onSuccess: () -> Unit = {}
) {
    observe(owner, Observer {
        when (it.status) {
            Status.LOADING -> onLoading(true)
            Status.SUCCESS -> {
                onLoading(false)
                onSuccess()
            }
            Status.ERROR -> {
                onLoading(false)
                it.error?.let(onError)
            }
        }
        it.data?.let(onNext)
    })
}

fun <T : Any> T.toLiveData() = object : LiveData<T>() {
    init {
        postValue(this@toLiveData)
    }
}
