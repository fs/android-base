package com.flatstack.android.model.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.apollographql.apollo.api.Response
import com.flatstack.android.model.entities.Resource
import com.flatstack.android.model.network.errors.ErrorHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

abstract class NetworkBoundResource<ResultType, RequestType>(
    override val coroutineContext: CoroutineContext,
    private val errorHandler: ErrorHandler
) : CoroutineScope {

    private val result = MutableLiveData<Resource<ResultType>>()

    init {
        result.postValue(Resource.loading())
        fetchFromNetwork()
    }

    fun asLiveData() = result as LiveData<Resource<ResultType>>

    fun fetchFromNetwork() {
        launch {
            result.postValue(Resource.loading(loadFromDb()))
            val apiResponse = createCallAsync().await()
            if (!apiResponse.hasErrors()) {
                saveCallResult(processResponse(apiResponse))
                result.postValue(Resource.success(loadFromDb()))
            } else {
                onFetchFailed()
                result.postValue(Resource.error(errorHandler.proceed(processErrorResponse(apiResponse)), loadFromDb()))
            }
        }
    }

    protected open fun onFetchFailed() {}

    protected open fun processResponse(response: Response<RequestType>) = response.data

    protected open fun processErrorResponse(response: Response<RequestType>) = response.errors?.get(0)

    protected abstract suspend fun saveCallResult(item: RequestType?)

    protected abstract suspend fun loadFromDb(): ResultType?

    protected abstract suspend fun createCallAsync(): Deferred<Response<RequestType>>
}
