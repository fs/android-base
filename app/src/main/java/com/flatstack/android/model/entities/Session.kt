package com.flatstack.android.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "session")
data class Session(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @SerializedName("access_token")
    val accessToken: String
)
