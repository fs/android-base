package com.flatstack.android.model.entities

import com.flatstack.android.model.entities.Status.ERROR
import com.flatstack.android.model.entities.Status.LOADING
import com.flatstack.android.model.entities.Status.SUCCESS

data class Resource<out T>(val status: Status, val data: T?, val error: String?) {
    companion object {
        fun <T> success(data: T? = null): Resource<T> {
            return Resource(SUCCESS, data, null)
        }

        fun <T> error(error: String, data: T? = null): Resource<T> {
            return Resource(ERROR, data, error)
        }

        fun <T> loading(data: T? = null): Resource<T> {
            return Resource(LOADING, data, null)
        }
    }
}
