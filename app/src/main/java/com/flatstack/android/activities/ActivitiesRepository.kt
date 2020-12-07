package com.flatstack.android.activities

import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Input
import com.apollographql.apollo.coroutines.toDeferred
import com.flatstack.android.fragment.ActivityConnectionFragment.Edge
import com.flatstack.android.fragment.ActivityConnectionFragment.PageInfo
import com.flatstack.android.graphql.query.GetActivitiesQuery
import com.flatstack.android.type.ActivityEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ActivitiesRepository(
    private val apolloClient: ApolloClient
) {
    fun getPagedUserActivities(coroutineScope: CoroutineScope, events: List<ActivityEvent>) =
        object : DataSource.Factory<PageInfo, Edge>() {
            override fun create(): DataSource<PageInfo, Edge> =
                object : PageKeyedDataSource<PageInfo, Edge>() {
                    override fun loadInitial(
                        params: LoadInitialParams<PageInfo>,
                        callback: LoadInitialCallback<PageInfo, Edge>
                    ) {
                        coroutineScope.launch(Dispatchers.IO) {
                            apolloClient.query(
                                GetActivitiesQuery(
                                    events = Input.fromNullable(events),
                                    first = Input.fromNullable(15)
                                )
                            ).toDeferred()
                                .await().data?.activities?.fragments?.activityConnectionFragment?.let {
                                it.edges?.let { edges ->
                                    callback.onResult(
                                        edges,
                                        null,
                                        it.pageInfo
                                    )
                                }
                            }
                        }
                    }

                    override fun loadAfter(
                        params: LoadParams<PageInfo>,
                        callback: LoadCallback<PageInfo, Edge>
                    ) {
                        coroutineScope.launch(Dispatchers.IO) {
                            apolloClient.query(
                                GetActivitiesQuery(
                                    after = Input.fromNullable(params.key.endCursor),
                                    events = Input.fromNullable(events),
                                    first = Input.fromNullable(15)
                                )
                            ).toDeferred()
                                .await().data?.activities?.fragments?.activityConnectionFragment?.let {
                                it.edges?.let { edges ->
                                    callback.onResult(
                                        edges,
                                        it.pageInfo
                                    )
                                }
                            }
                        }
                    }

                    override fun loadBefore(
                        params: LoadParams<PageInfo>,
                        callback: LoadCallback<PageInfo, Edge>
                    ) {
                        coroutineScope.launch(Dispatchers.IO) {
                            apolloClient.query(
                                GetActivitiesQuery(
                                    before = Input.fromNullable(params.key.startCursor),
                                    events = Input.fromNullable(events),
                                    first = Input.fromNullable(15)
                                )
                            ).toDeferred()
                                .await().data?.activities?.fragments?.activityConnectionFragment?.let {
                                it.edges?.let { edges ->
                                    callback.onResult(
                                        edges,
                                        it.pageInfo
                                    )
                                }
                            }
                        }
                    }
                }
        }
}
