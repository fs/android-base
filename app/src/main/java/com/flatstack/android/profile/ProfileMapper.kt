package com.flatstack.android.profile

import com.flatstack.android.graphql.query.GetUserQuery
import com.flatstack.android.profile.entities.Profile

object ProfileMapper {
    fun mapProfile(me: GetUserQuery.Me?) = me?.fragments?.userGqlFragment.run {
        Profile(
            firstName = this?.firstName ?: "",
            lastName = this?.lastName ?: ""
        )
    }
}
