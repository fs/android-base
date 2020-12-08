package com.flatstack.android.activities

import androidx.lifecycle.*
import androidx.paging.PagedList
import com.flatstack.android.model.entities.Resource
import com.flatstack.android.type.ActivityEvent
import com.flatstack.android.type.ActivityEvent.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

class ActivitiesViewModel(
    private val activitiesRepository: ActivitiesRepository
) : ViewModel() {

    private val events = MutableLiveData<List<ActivityEvent>>()

    init {
        events.postValue(
            listOf(
                RESET_PASSWORD_REQUESTED,
                USER_LOGGED_IN,
                USER_REGISTERED,
                USER_RESET_PASSWORD,
                USER_UPDATED
            )
        )
    }

    val activities: LiveData<Resource<PagedList<ActivitiesViewHolderModel?>>> =
        events.switchMap { events ->
            activitiesRepository.getPagedUserActivities(viewModelScope, events)
                .flowOn(Dispatchers.IO)
                .map { Resource.success(it) }
                .onStart { emit(Resource.loading()) }
                .catch { error ->
                    error.message?.let {
                        emit(Resource.error(it))
                    }
                }
                .asLiveData(viewModelScope.coroutineContext)
        }
}
