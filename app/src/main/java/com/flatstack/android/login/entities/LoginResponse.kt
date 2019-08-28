package com.flatstack.android.login.entities

import com.flatstack.android.model.entities.Session
import com.google.gson.annotations.SerializedName

data class LoginResponse(@SerializedName("session") val session: Session)
