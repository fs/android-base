package com.flatstack.android.model.network.adapter

import com.flatstack.android.model.network.ApiResponse
import kotlinx.coroutines.Deferred
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

@Suppress("UnsafeCallOnNullableType")
class CoroutineCallAdapterFactory private constructor() : CallAdapter.Factory() {

    companion object {
        @JvmStatic
        @JvmName("create")
        operator fun invoke() = CoroutineCallAdapterFactory()
    }

    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        if (Deferred::class.java != getRawType(returnType)) {
            return null
        }
        if (returnType !is ParameterizedType) {
            throw IllegalStateException(
                "Deferred return type must be parameterized as Deferred<Foo> or Deferred<out Foo>")
        }
        val responseType = getParameterUpperBound(0, returnType)

        val rawDeferredType = getRawType(responseType)
        return if (rawDeferredType == ApiResponse::class.java) {
            if (responseType !is ParameterizedType) {
                throw IllegalStateException("Response must be parameterized as Response<Foo> or Response<out Foo>")
            }
            CoroutineCallAdapter<Any>(getParameterUpperBound(0, responseType))
        } else {
            BodyCallAdapter<Any>(responseType)
        }
    }
}
