package com.flatstack.android.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "session")
data class Session(
    val accessToken: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)
