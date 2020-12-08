package com.flatstack.android.activities

import com.flatstack.android.profile.entities.Profile
import com.flatstack.android.type.ActivityEvent

data class ActivitiesViewHolderModel(
    val id: String,
    val body: String,
    val createdAt: String,
    val event: ActivityEvent,
    val title: String,
    val user: Profile
)
