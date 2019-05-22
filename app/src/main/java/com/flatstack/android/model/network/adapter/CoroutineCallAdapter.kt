package com.flatstack.android.model.network.adapter

import com.flatstack.android.model.network.ApiResponse
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type

internal class CoroutineCallAdapter<T>(private val responseType: Type) : CallAdapter<T, Deferred<ApiResponse<T>>> {

    override fun responseType() = responseType

    override fun adapt(call: Call<T>): Deferred<ApiResponse<T>> {
        val deferred = CompletableDeferred<ApiResponse<T>>()
        deferred.invokeOnCompletion {
            if (deferred.isCancelled && it is CancellationException) {
                call.cancel()
            }
        }
        call.enqueue(object : Callback<T> {
            override fun onFailure(call: Call<T>, throwable: Throwable) {
                deferred.complete(ApiResponse.create(throwable))
            }

            override fun onResponse(call: Call<T>, response: Response<T>) {
                deferred.complete(ApiResponse.create(response))
            }
        })
        return deferred
    }
}
