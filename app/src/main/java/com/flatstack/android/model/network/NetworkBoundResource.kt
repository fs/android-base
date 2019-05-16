package com.flatstack.android.model.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
            when (val apiResponse = createCallAsync().await()) {
                is ApiSuccessResponse -> {
                    saveCallResult(processResponse(apiResponse))
                    result.postValue(Resource.success(loadFromDb()))
                }
                is ApiErrorResponse -> {
                    onFetchFailed()
                    result.postValue(Resource.error(errorHandler.proceed(apiResponse.error), loadFromDb()))
                }
            }
        }
    }

    protected open fun onFetchFailed() {}

    protected open fun processResponse(response: ApiSuccessResponse<RequestType>) = response.body

    protected abstract suspend fun saveCallResult(item: RequestType)

    protected abstract suspend fun loadFromDb(): ResultType?

    protected abstract suspend fun createCallAsync(): Deferred<ApiResponse<RequestType>>
}
